package person;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TestUtil {
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("Database");
    public static final String URL = RESOURCE_BUNDLE.getString("URL");
    private static Connection connection;


    public static void closeConnection() throws SQLException {
        connection.close();
    }


    public static void createPersonTable() throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        connection = new H2ConnectionSupplier().getConnection();
        connection.setAutoCommit(false);
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("CREATE TABLE public.person(" +
                    "id identity primary key," +
                    "name varchar(25) null," +
                    "surname varchar(25) null)");
            preparedStatement.executeQuery();
            connection.commit();
            preparedStatement.close();
        } catch (SQLException e) {
            connection.rollback();
        }
    }
}
