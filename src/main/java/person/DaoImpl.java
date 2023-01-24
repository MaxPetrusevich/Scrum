package Person;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
public class DAOImpl<T> implements DAO<T> {


    private Connection conn;
    private DataForTable<T> data;

    public DAOImpl(DataForTable<T> data) throws SQLException {
        this.data = data;
        this.conn = MyConnection.getConnection();
    }

    @Override
    public T save(T object) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        data.setObject(object);
        conn.setAutoCommit(false);
        try {
            String sql = SQLQuery.getInsertQuery(data);
            preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            SQLQuery.setValues(preparedStatement, data);
            preparedStatement.executeUpdate();
            rs = preparedStatement.getGeneratedKeys();
            rs.next();
            int row = rs.getInt(1);
            Method method = receiveSetId(data);
            if (method != null) {
                method.invoke(object, row);
            }
            String sqlSelect = SQLQuery.getSelectQuery(data);
            preparedStatement = conn.prepareStatement(sqlSelect);
            rs = preparedStatement.executeQuery();
            SQLQuery.viewSelectAllResult(rs, data.getFields());
            conn.commit();
        } catch (SQLException | IllegalAccessException | InvocationTargetException e) {
            conn.rollback();
            e.printStackTrace();
        } finally {
            finalOfCheckExceptions(preparedStatement,  rs);
        }
        return object;
    }

    private void finalOfCheckExceptions(PreparedStatement preparedStatement,  ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    private static Method receiveSetId(DataForTable<?> data) {
        for (Method method1 :
                data.getMethods()) {
            if (method1.getName().compareTo("setId") == 0) {
                return method1;
            }
        }
        return null;
    }


    @Override
    public T get(int id) throws SQLException {

        PreparedStatement preparedStatementSelect = null;
        ResultSet rs = null;
        T object = null;
        conn.setAutoCommit(false);
        try {
            String sqlSelect = SQLQuery.getSelectByIdQuery(data);
            preparedStatementSelect = conn.prepareStatement(sqlSelect);
            SQLQuery.setValues(preparedStatementSelect, id);
            rs = preparedStatementSelect.executeQuery();
            rs.next();
            object = getObjectFromResultSet(rs);
            conn.commit();
        } catch (SQLException | IllegalAccessException | InvocationTargetException
                 | NoSuchMethodException | InstantiationException e) {
            conn.rollback();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (preparedStatementSelect != null) {
                    preparedStatementSelect.close();
                }

            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }

        return object;

    }

    @Override
    public void update(T object) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        data.setObject(object);
        conn.setAutoCommit(false);
        try {
            Field[] fields = data.getFields();
            List<String> columns = data.getColumns();
            Integer valuesCount = 1;
            String updateSQL = SQLQuery.getUpdateQuery(data);
            preparedStatement = conn.prepareStatement(updateSQL);
            for (int i = 0; i < fields.length; i++) {
                valuesCount = SQLQuery.setUpdateValue(data, preparedStatement, fields[i], columns.get(i), valuesCount);
            }
            preparedStatement.executeUpdate();
            String sqlSelect = SQLQuery.getSelectQuery(data);
            preparedStatement = conn.prepareStatement(sqlSelect);
            rs = preparedStatement.executeQuery();
            SQLQuery.viewSelectAllResult(rs, fields);
            conn.commit();
        } catch (SQLException | IllegalAccessException | InvocationTargetException e) {
            conn.rollback();
            e.printStackTrace();
        } finally {
            finalOfCheckExceptions(preparedStatement,  rs);
        }
    }

    @Override
    public int delete(int id) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        int rowsAffected = 0;
        conn.setAutoCommit(false);
        try {
            String sqlDelete = SQLQuery.getDeleteByIdQuery(data);
            preparedStatement = conn.prepareStatement(sqlDelete);
            SQLQuery.setValues(preparedStatement, id);
            rowsAffected = preparedStatement.executeUpdate();
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

            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }
        return rowsAffected;
    }

    private T getObjectFromResultSet(ResultSet rs)
            throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        Field[] fields = data.getFields();
        Method[] methods = data.getMethods();
        Constructor<?> constructor = data.getObject().getClass().getConstructor();
        T object = (T) constructor.newInstance(null);
        for (int i = 0; i < fields.length; i++) {
            Class<?> type = fields[i].getType();
            Method method = null;
            for (Method method1 :
                    methods) {
                if (method1.getName().toLowerCase().compareTo("set" + fields[i].getName().toLowerCase()) == 0) {
                    method = method1;
                    break;
                }
            }
            if (method != null) {
                if (type == Integer.class || type == Byte.class || type == Short.class) {
                    method.invoke(object, rs.getInt(i + 1));
                } else if (type == Long.class) {
                    method.invoke(object, rs.getLong(i + 1));
                } else if (type == Boolean.class) {
                    method.invoke(object, rs.getBoolean(i + 1));
                } else if (type == String.class || type == Character.class) {
                    method.invoke(object, rs.getString(i + 1));
                }
            }

        }
        return object;
    }
}
