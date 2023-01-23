package Person;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class MyConnectionTest {

    @Test
    public void getConnection() throws SQLException {
        Connection actualConnection = MyConnection.getConnection();
        Assert.assertNotNull( "connection should be successful", actualConnection);
    }
}