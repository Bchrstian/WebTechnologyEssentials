import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Register {
    String url = "jdbc:postgresql://localhost:5432/registrationdb";
    String user = "postgres";
    String password = "A$aprocky08";
    String driver = "org.postgresql.Driver"; 

    public boolean registerUser(Member member) {
        RegisterDao registerDao = new RegisterDao();
        return registerDao.insert(member);
    }
}
