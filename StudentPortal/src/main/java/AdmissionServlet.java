import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/AdmissionServlet")
public class AdmissionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve data from the form

        // Assuming the user is logged in, retrieve session attributes
        HttpSession userSession = request.getSession(false);
        if (userSession == null) {
            // Redirect to login page if the user is not logged in
            response.sendRedirect("index.jsp");
            return;
        }

        String firstName = (String) userSession.getAttribute("firstName");
        String lastName = (String) userSession.getAttribute("lastName");
        String department = (String) userSession.getAttribute("department");
        String email = (String) userSession.getAttribute("email");

        // Save uploaded files to the server or database if needed
        // For simplicity, we are not saving the files in this example

        // Send confirmation email
        sendConfirmationEmail(email, firstName, department);

        // Display a success message
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h1>Admission Successful!</h1>");
        out.println("<p>Congratulations, " + firstName + " " + lastName + "! You have been admitted to the " + department + " department.</p>");
    }

    private void sendConfirmationEmail(String recipientEmail, String recipientName, String department) {
        String host = "smtp.gmail.com";
        String port = "587";
        String username = "christian.urugoconnect@gmail.com";
        String password = "kmhx ojkr uybv gyou";

        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.debug", "true"); // Enable debugging

        // Explicitly specify TLS version (TLSv1.3 or TLSv1.2, adjust based on server compatibility)
        properties.put("mail.smtp.ssl.protocols", "TLSv1.3");

        // Specify allowed cipher suite (TLS_AES_128_GCM_SHA256 or others, adjust based on server compatibility)
        properties.put("mail.smtp.ssl.ciphersuites", "TLS_AES_128_GCM_SHA256");

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("Admission Confirmation");

            String confirmationMessage = "Dear " + recipientName + ",\n\n"
                    + "Congratulations! You have been successfully admitted to the " + department + " department.\n"
                    + "Welcome to our community!\n\n"
                    + "Best regards,\nThe Admission Team";

            message.setText(confirmationMessage);

            Transport.send(message);

            System.out.println("Confirmation email sent to " + recipientEmail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
