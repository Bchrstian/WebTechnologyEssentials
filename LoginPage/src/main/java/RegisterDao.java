import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterDao {
    String url = "jdbc:postgresql://localhost:5432/registrationdb";
    String user = "postgres";
    String password = "A$aprocky08";

    public boolean insert(Member member) {
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
