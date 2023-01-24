package person;

import lombok.Getter;

import java.sql.Connection;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class TestUtil {
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("Database");
    public static final String URL = RESOURCE_BUNDLE.getString("URL");
    private static Connection connection;


    public static void closeConnection() throws SQLException {
        connection.close();
    }

    public static Connection getConnection(){
        return connection;
    }
    public static void createPersonTable() throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        connection = new H2ConnectionSupplier().getConnection();
        connection.setAutoCommit(false);
        try{
            Statement statement = connection.createStatement();
            statement.executeQuery("create table person( " +
                     "id identity primary key " +
                    " name varchar(255) " +
                    " surname varchar(255))");
            connection.commit();
            statement.close();
        } catch (SQLException e) {
            connection.rollback();
        }
    }
}
