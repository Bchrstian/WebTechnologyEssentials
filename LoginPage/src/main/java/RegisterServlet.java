import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    static {
        try {
            // Explicitly load the PostgreSQL JDBC driver
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String password = request.getParameter("password");

        Member member = new Member(firstname, lastname, email, phoneNumber, password);

        if (registerUser(member)) {
            // Registration successful
            response.sendRedirect("registration-success.jsp");
        } else {
            // Registration failed
            response.sendRedirect("registration-failure.jsp");
        }
    }

    private boolean registerUser(Member member) {
        String url = "jdbc:postgresql://localhost:5432/registrationdb";
        String user = "postgres";
        String password = "A$aprocky08";

        try {
            // Establish the database connection
            try (Connection connection = DriverManager.getConnection(url, user, password)) {

                // SQL query to insert user data into the database
                String sql = "INSERT INTO users (firstname, lastname, email, phone_number, password) VALUES (?, ?, ?, ?, ?)";

                // Create a PreparedStatement
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                    // Set values for the parameters
                    preparedStatement.setString(1, member.getFirstname());
                    preparedStatement.setString(2, member.getLastname());
                    preparedStatement.setString(3, member.getEmail());
                    preparedStatement.setString(4, member.getPhoneNumber());
                    preparedStatement.setString(5, member.getPassword());

                    // Execute the query
                    int rowsAffected = preparedStatement.executeUpdate();

                    // Check if the registration was successful
                    return rowsAffected > 0;
                }
            }
        } catch (SQLException e) {
            // Log the exception or handle it appropriately
            e.printStackTrace();
        }

        // Registration failed
        return false;
    }
}
