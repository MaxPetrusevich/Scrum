package Person;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;



public class MyConnection {
    public static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("Database");
    public static final String PASSWORD = RESOURCE_BUNDLE.getString("password");
    public static final String USER = RESOURCE_BUNDLE.getString("user");
    public static final String URL = RESOURCE_BUNDLE.getString("URL");

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL,
                USER,
                PASSWORD);
    }
}
