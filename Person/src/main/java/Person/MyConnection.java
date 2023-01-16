package Person;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static Person.DAOPersonImpl.*;

public class MyConnection {
    public static Connection getConnection() throws SQLException {
        Connection conn = null;
        conn = DriverManager.getConnection(URL,
                USER,
                PASSWORD);
        return conn;
    }
}
