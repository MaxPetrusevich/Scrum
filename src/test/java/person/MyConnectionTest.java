package person;

import org.junit.Assert;
import org.junit.Test;
import person.util.MyConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class MyConnectionTest {

    @Test
    public void getConnection() throws SQLException {
        Connection actualConnection = MyConnection.getConnection();
        Assert.assertNotNull( "connection should be successful", actualConnection);
    }
}