import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
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
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        System.out.println("Email: " + email);  // Debugging statement
        System.out.println("Password: " + password);  // Debugging statement

        if (validateUser(email, password)) {
            // Login successful
            HttpSession session = request.getSession();
            session.setAttribute("email", email);

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            // Display an alert using JavaScript
            out.println("<script type='text/javascript'>");
            out.println("alert('Login successful. Welcome!');");
            out.println("window.location.href='welcome.jsp';");
            out.println("</script>");
        } else {
            // Login failed
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            // Display an alert using JavaScript
            out.println("<script type='text/javascript'>");
            out.println("alert('Invalid email or password. Please try again.');");
            out.println("window.history.back();"); // Go back to the previous page
            out.println("</script>");
        }
    }

    private boolean validateUser(String email, String password) {
        String url = "jdbc:postgresql://localhost:5432/registrationdb";
        String user = "postgres";
        String dbPassword = "A$aprocky08";

        try {
            // Explicitly load the PostgreSQL JDBC driver
            Class.forName("org.postgresql.Driver");

            // Establish the database connection
            try (Connection connection = DriverManager.getConnection(url, user, dbPassword)) {
                // SQL query to check if the user exists
                String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, email);
                    preparedStatement.setString(2, password);

                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        // If any row is returned, the user exists with the given credentials
                        return resultSet.next();
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        // Validation failed
        return false;
    }
}
