package person.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import person.dao.Dao;
import person.util.DataForTable;
import person.util.MyConnection;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;


@AllArgsConstructor
@NoArgsConstructor
@Setter
public class DaoImpl<T> implements Dao<T> {


    public static final String SET = "set";
    public static final int COLUMN_SAVE_INDEX = 1;
    private Connection conn;
    private DataForTable<T> data;

    public DaoImpl(DataForTable<T> data) throws SQLException {
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
            String sql = SqlQuery.getInsertQuery(data);
            preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            SqlQuery.setValues(preparedStatement, data);
            preparedStatement.executeUpdate();
            rs = preparedStatement.getGeneratedKeys();
            rs.next();
            int row = rs.getInt(COLUMN_SAVE_INDEX);
            Field primaryKey = data.getPrimaryKey();
            primaryKey.setAccessible(true);
            primaryKey.set(object, row);
            primaryKey.setAccessible(false);
            String sqlSelect = SqlQuery.getSelectQuery(data);
            preparedStatement = conn.prepareStatement(sqlSelect);
            rs = preparedStatement.executeQuery();
            SqlQuery.viewSelectAllResult(rs, data.getFields());
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



    @Override
    public T get(int id) throws SQLException {

        PreparedStatement preparedStatementSelect = null;
        ResultSet rs = null;
        T object = null;
        conn.setAutoCommit(false);
        try {
            String sqlSelect = SqlQuery.getSelectByIdQuery(data);
            preparedStatementSelect = conn.prepareStatement(sqlSelect);
            SqlQuery.setValues(preparedStatementSelect, id);
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
            Integer valuesCount = 1;
            String updateSQL = SqlQuery.getUpdateQuery(data);
            preparedStatement = conn.prepareStatement(updateSQL);
            for (Field field:
            fields) {
                valuesCount = SqlQuery.setUpdateValue(data, preparedStatement, field,  valuesCount);
            }
            preparedStatement.executeUpdate();
            String sqlSelect = SqlQuery.getSelectQuery(data);
            preparedStatement = conn.prepareStatement(sqlSelect);
            rs = preparedStatement.executeQuery();
            SqlQuery.viewSelectAllResult(rs, fields);
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
            String sqlDelete = SqlQuery.getDeleteByIdQuery(data);
            preparedStatement = conn.prepareStatement(sqlDelete);
            SqlQuery.setValues(preparedStatement, id);
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
        Constructor<?> constructor = data.getObject().getClass().getConstructor();
        T object = (T) constructor.newInstance(null);
        for (int i = 0; i < fields.length; i++) {
            Class<?> type = fields[i].getType();
            fields[i].setAccessible(true);

            if (type == Integer.class || type == Byte.class || type == Short.class) {
                    fields[i].set(object, rs.getInt(i + 1));
                } else if (type == Long.class) {
                    fields[i].set(object, rs.getLong(i + 1));
                } else if (type == Boolean.class) {
                    fields[i].set(object,  rs.getBoolean(i + 1));
                } else if (type == String.class || type == Character.class) {
                    fields[i].set(object, rs.getString(i + 1));
                }
            fields[i].setAccessible(false);
            }
        return object;
    }
}
