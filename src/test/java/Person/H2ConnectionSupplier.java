package Person;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;

public class H2ConnectionSupplier  {
    public static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("DatabaseH2");

    private final String DB_URL = RESOURCE_BUNDLE.getString("URL");
    private final String DB_USER = RESOURCE_BUNDLE.getString("user");
    private final String DB_PASSWORD = RESOURCE_BUNDLE.getString("password");


    public Connection getConnection() throws SQLException {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            connection.setAutoCommit(true);
            return connection;
        } catch (SQLException  e) {
            e.printStackTrace();
            throw new SQLException("No connection with H2 Data Base");
        }
    }



}