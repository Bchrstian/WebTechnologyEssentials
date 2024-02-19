import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    static {
        // Load the PostgreSQL JDBC driver
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String password = request.getParameter("password");
        String department = request.getParameter("department");

        if (saveUser(firstName, lastName, email, phoneNumber, password, department)) {
            // Registration successful
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            // Display an alert using JavaScript
            out.println("<script type='text/javascript'>");
            out.println("alert('Registration successful. Welcome!');");
            out.println("window.location.href='index.jsp#loginModal';");
            out.println("</script>");
        } else {
            // Registration failed
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            // Display an alert using JavaScript
            out.println("<script type='text/javascript'>");
            out.println("alert('Registration failed. Please try again.');");
            out.println("window.history.back();"); // Go back to the previous page
            out.println("</script>");
        }
    }

    private boolean saveUser(String firstName, String lastName, String email, String phoneNumber, String password,
            String department) {
        String url = "jdbc:postgresql://localhost:5432/student_portal";
        String user = "postgres";
        String dbPassword = "A$aprocky08";

        try {
            // Establish the database connection
            try (Connection connection = DriverManager.getConnection(url, user, dbPassword)) {
                // SQL query to insert the new user
                String sql = "INSERT INTO users (firstName, lastName, email, phoneNumber, password, department) VALUES (?, ?, ?, ?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, firstName);
                    preparedStatement.setString(2, lastName);
                    preparedStatement.setString(3, email);
                    preparedStatement.setString(4, phoneNumber);
                    preparedStatement.setString(5, password);
                    preparedStatement.setString(6, department);

                    // Execute the query
                    int rowsAffected = preparedStatement.executeUpdate();

                    // If at least one row is affected, the user is successfully added
                    return rowsAffected > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Registration failed
        return false;
    }
}
