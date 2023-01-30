package person.utilTest;


import org.junit.jupiter.api.Test;
import person.util.MyConnection;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MyConnectionTest {

    @Test
    public void getConnection() throws SQLException {
        Connection actualConnection = MyConnection.getConnection();
        assertNotNull(actualConnection, "connection should be successful");
    }
}