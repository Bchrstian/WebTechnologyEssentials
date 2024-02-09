import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//... (imports)

@WebServlet("/ForgotPasswordServlet")
public class ForgotPasswordServlet extends HttpServlet {
 protected void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
     String email = request.getParameter("forgetEmailInput");

     // Generate a unique token
     String token = UUID.randomUUID().toString();

     // Database connection details
     String jdbcUrl = "jdbc:postgresql://localhost:5432/registrationdb";
     String dbUser = "postgres";
     String dbPassword = "A$aprocky08";

     try {
         Class.forName("org.postgresql.Driver");
         Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);

         // Check if the email exists in the database
         String checkEmailQuery = "SELECT * FROM users WHERE email = ?";
         try (PreparedStatement checkEmailStmt = connection.prepareStatement(checkEmailQuery)) {
             checkEmailStmt.setString(1, email);
             try (ResultSet resultSet = checkEmailStmt.executeQuery()) {
                 if (resultSet.next()) {
                     // If the email exists, update the token and token expiration time
                     Timestamp expirationTime = new Timestamp(System.currentTimeMillis() + 3600000); // 1 hour expiration
                     String updateTokenQuery = "UPDATE users SET reset_token = ?, token_expiration = ? WHERE email = ?";
                     try (PreparedStatement updateTokenStmt = connection.prepareStatement(updateTokenQuery)) {
                         updateTokenStmt.setString(1, token);
                         updateTokenStmt.setTimestamp(2, expirationTime);
                         updateTokenStmt.setString(3, email);
                         updateTokenStmt.executeUpdate();
                     }

                     // TODO: Send an email to the user with a link including the token
                     // You would typically send an email to the user with a link like:
                     // "https://yourwebsite.com/resetPassword?token=generatedToken"

                     // Display a pop-up message
                     PrintWriter out = response.getWriter();
                     out.println("<script type='text/javascript'>");
                     out.println("alert('A password reset link has been sent to your email.');");
                     out.println("window.history.back();"); // Go back to the previous page
                     out.println("</script>");
                 } else {
                     // Email doesn't exist in the database
                     // Display a pop-up message
                     PrintWriter out = response.getWriter();
                     out.println("<script type='text/javascript'>");
                     out.println("alert('Email not found. Please enter a valid email.');");
                     out.println("window.history.back();"); // Go back to the previous page
                     out.println("</script>");
                 }
             }
         }

         connection.close();
     } catch (Exception e) {
         e.printStackTrace();
         // Display a pop-up message for any other error
         PrintWriter out = response.getWriter();
         out.println("<script type='text/javascript'>");
         out.println("alert('An error occurred. Please try again later.');");
         out.println("window.history.back();"); // Go back to the previous page
         out.println("</script>");
     }
 }
}
