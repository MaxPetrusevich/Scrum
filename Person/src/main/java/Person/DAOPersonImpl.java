package Person;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class DAOPersonImpl implements DAOPerson {

    public static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("Database");
    public static final String PASSWORD = RESOURCE_BUNDLE.getString("password");
    public static final String USER = RESOURCE_BUNDLE.getString("user");
    public static final String URL = RESOURCE_BUNDLE.getString("URL");
    public static final String TABLE_NAME = Person.class.getDeclaredAnnotation(MyTable.class).name();
    public static final Field[] FIELDS = Person.class.getDeclaredFields();
    public static final List<String> COLUMNS = ColumnsName.getColumnsName(FIELDS);

    @Override
    public Person save(Person person) throws SQLException, ClassNotFoundException, NoSuchMethodException {

        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatementSelect = null;
        ResultSet rs = null;
        Connection conn = MyConnection.getConnection();
        conn.setAutoCommit(false);
        try {
            String sql = "insert into " + TABLE_NAME + " (" + COLUMNS.get(1) + " , " + COLUMNS.get(2)
                    + " ) " + "values ('?', '*')";
            sql = sql.replace("?", person.getName());
            sql = sql.replace("*", person.getSurname());
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            rs = preparedStatement.getGeneratedKeys();
            rs.next();
            int row = rs.getInt(1);
            person.setId(row);
            String sqlSelect = "select * from " + TABLE_NAME +
                    " order by id desc";
            preparedStatementSelect = conn.prepareStatement(sqlSelect);
            rs = preparedStatementSelect.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2)
                        + " " + rs.getString(3));
            }
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }

                if (preparedStatementSelect != null) {
                    preparedStatementSelect.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return person;
    }




    @Override
    public Person get(int id) throws SQLException {

        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatementSelect = null;
        ResultSet rs = null;
        Person person = null;
        Connection conn = MyConnection.getConnection();
        conn.setAutoCommit(false);
        try {
            String sqlSelect = "select * from " + TABLE_NAME + " where " + COLUMNS.get(0) + " = '?'" +
                    "order by id desc";
            sqlSelect = sqlSelect.replace("?", Integer.toString(id));
            preparedStatementSelect = conn.prepareStatement(sqlSelect);
            rs = preparedStatementSelect.executeQuery();
            while (rs.next()) {
                person = Person.builder().id(rs.getInt(1)).name(rs.getString(2))
                        .surname(rs.getString(3)).build();
            }
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }

                if (preparedStatementSelect != null) {
                    preparedStatementSelect.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return person;
    }

    @Override
    public int delete(int id) throws SQLException {
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatementSelect = null;
        ResultSet rs = null;
        int rows = 0;
        Connection conn = MyConnection.getConnection();
        conn.setAutoCommit(false);
        try {
            String sqlDelete = "delete " + TABLE_NAME + " from " + TABLE_NAME + " where " + COLUMNS.get(0) + " = '?'";
            sqlDelete = sqlDelete.replace("?", Integer.toString(id));
            preparedStatementSelect = conn.prepareStatement(sqlDelete);
            rows = preparedStatementSelect.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }

                if (preparedStatementSelect != null) {
                    preparedStatementSelect.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return rows;
    }

    @Override
    public void update(Person person) throws SQLException {
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatementSelect = null;
        ResultSet rs = null;
        Connection conn = MyConnection.getConnection();
        conn.setAutoCommit(false);
        try{
            String sql = "update " + TABLE_NAME +
                    " set " + COLUMNS.get(1) + " = '?'" +
                    " where " + COLUMNS.get(0) + " = '!'";
            sql = sql.replace("?", person.getName());
            sql = sql.replace("!", Integer.toString(person.getId()));
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.executeUpdate();
            sql = "update " + TABLE_NAME +
                    " set " + COLUMNS.get(2) + " = '*'" +
                    " where " + COLUMNS.get(0) + " = '!'";
            sql = sql.replace("*", person.getSurname());
            sql = sql.replace("!", Integer.toString(person.getId()));
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.executeUpdate();
            String sqlSelect = "select * from " + TABLE_NAME +
                    " order by "+ COLUMNS.get(0) + " desc";

            preparedStatementSelect = conn.prepareStatement(sqlSelect);
            rs = preparedStatementSelect.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2) +
                        " " + rs.getString(3));
            }
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }

                if (preparedStatementSelect != null) {
                    preparedStatementSelect.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
