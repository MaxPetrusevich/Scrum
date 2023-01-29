package person.service;

import person.util.DataForTable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * It is SqlQuery class.
 *
 * @author Scrum team.
 *
 */
import static person.data.Constants.*;
public class SqlQuery {





    /**
     * It is getInsertQuery method.
     *
     * @param data from generic Class
     *
     * @return insert.
     */
    public static String getInsertQuery(DataForTable<?> data) {
        String insert = INSERT_INTO + data.getTableName() + OPEN_BRACKET;
        String primaryKey = data.getPrimaryKey();
        List<String> columnsName = data.getColumns();
        for (String column :
                columnsName) {
            if (!(column.toLowerCase().compareTo(primaryKey.toLowerCase()) == 0)) {
                insert = insert.concat(column + COMMA);
            }
        }
        insert = insert.replaceFirst(REGEX_FOR_VALUES, CLOSE_BRACKET);
        insert = insert.concat(VALUES);
        for (String column :
                columnsName) {
            if (!(column.toLowerCase().compareTo(primaryKey.toLowerCase()) == 0)) {
                insert = insert.concat(QUSTION);
            }
        }
        insert = insert.replaceFirst(REGEX_FOR_END_OF_QUERY, CLOSE_BRACKET);
        return insert;
    }

    /**
     * It is setValues method.
     *
     */
    public static void setValues(PreparedStatement ps, DataForTable<?> data)
            throws IllegalAccessException, InvocationTargetException, SQLException {
        int valuesCount = 1;
        Field[] fields = data.getFields();
        List<Method> getters = data.receiveGetters();
        String primaryKey = data.getPrimaryKey();
        for (Field field :
                fields) {
            if (field.getName().toLowerCase().compareTo(primaryKey.toLowerCase()) == 0){
                continue;
            }
            Method getter = null;
            for (Method method :
                    getters) {
                if (method.getName().toLowerCase().compareTo("get"
                    + field.getName().toLowerCase()) == 0) {
                    getter = method;
                    break;
                }
            }
            setValue(data, ps, field, getter, valuesCount);
            valuesCount++;
        }
    }


    public static void setValues(PreparedStatement ps, int id) throws SQLException {
        ps.setInt(1, id);
    }

    /**
     * It is viewSelectAllResult method.
     *
     */
    public static void viewSelectAllResult(ResultSet rs, Field[] fields)
            throws SQLException {
        int fieldsCount = fields.length;
        while (rs.next()) {
            for (int i = 0; i < fieldsCount; i++) {
                Class<?> type = fields[i].getType();
                if (type == Integer.class || type == Byte.class || type == Short.class) {
                    System.out.print(rs.getInt(i + 1) + " ");
                } else if (type == Long.class) {
                    System.out.print(rs.getLong(i + 1));
                } else if (type == Boolean.class) {
                    System.out.print(rs.getBoolean(i + 1) + " ");
                } else if (type == String.class || type == Character.class) {
                    System.out.print(rs.getString(i + 1) + " ");
                }
            }
            System.out.println();
        }
    }

    public static String getSelectQuery(DataForTable<?> data) {
        return "select * from " + data.getTableName() + " order by id";
    }

    /**
     * It is getSelectByIdQuery method.
     *
     * @param data from generic class
     *
     * @return select
     */
    public static String getSelectByIdQuery(DataForTable<?> data) {
        List<String> columns = data.getColumns();
        String columnName = null;
        String primaryKey = data.getPrimaryKey();
        for (String column :
                columns) {
            if (column.toLowerCase().compareTo(primaryKey.toLowerCase()) == 0) {
                columnName = column;
                break;
            }
        }
        return SELECT_FROM + data.getTableName() + WHERE + columnName + CONDITION;
    }


    /**
     * It is getUpdateQuery method.
     *
     * @param data from generic class
     *
     * @return update
     */
    public static String getUpdateQuery(DataForTable<?> data)
            throws IllegalAccessException, InvocationTargetException {
        String update = UPDATE + data.getTableName() + SET;
        List<String> columnsName = data.getColumns();
        String primaryKey = data.getPrimaryKey();
        for (String column :
                columnsName) {
            if (!(column.toLowerCase().compareTo(primaryKey.toLowerCase()) == 0)) {
                update = update.concat(column + SET_FOR_UPDATE);
            }
        }
        update = update.replaceFirst(REGEX_FOR_VALUES, CONDITION_FOR_UPDATE);
        Field[] fields = data.getFields();
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getName().toLowerCase().compareTo(primaryKey.toLowerCase()) == 0) {
                update = update.replace(STAR, columnsName.get(i));
                break;
            }
        }
        return update;
    }

    /**
     * It is setUpdateValue method.
     *
     */
    public static int setUpdateValue(DataForTable<?> data, PreparedStatement ps,
                                     Field field, int valuesCount)
            throws SQLException, IllegalAccessException, InvocationTargetException {
        Method[] methods = data.getMethods();
        String primaryKey = data.getPrimaryKey();
        if (field.getName().toLowerCase().compareTo(primaryKey.toLowerCase()) == 0) {
            int idSetNumber = data.getFields().length;
            for (Method method :
                    methods) {
                if (method.getName().toLowerCase().compareTo(GET + field.getName()) == 0) {
                    ps.setInt(idSetNumber, (Integer) method.invoke(data.getObject()));
                }
            }
        } else {
            for (Method method :
                    methods) {
                if (method.getName().toLowerCase().compareTo(GET + field.getName()) == 0) {
                    setValue(data, ps, field, method, valuesCount);
                    valuesCount = valuesCount + 1;

                }
            }
        }
        return valuesCount;
    }


    /**
     * It is setValue method.
     *
     */
    private static void setValue(DataForTable<?> data, PreparedStatement ps, Field field,
                                 Method method, int parameterIndex) throws SQLException,
            IllegalAccessException, InvocationTargetException {
        Class<?> type = field.getType();
        if (type == Integer.class || type == Byte.class || type == Short.class) {
            ps.setInt(parameterIndex, (Integer) method.invoke(data.getObject()));
        } else if (type == Long.class) {
            ps.setLong(parameterIndex, (Long) method.invoke(data.getObject()));
        } else if (type == Boolean.class) {
            ps.setBoolean(parameterIndex, (Boolean) method.invoke(data.getObject()));
        } else if (type == String.class || type == Character.class) {
            ps.setString(parameterIndex, (String) method.invoke(data.getObject()));
        }
    }

    /**
     * It is getDeleteByIdQuery method.
     *
     * @return delete
     */
    public static String getDeleteByIdQuery(DataForTable<?> data) {
        List<String> columns = data.getColumns();
        String columnName = null;
        String primaryKey = data.getPrimaryKey();
        for (String column :
                columns) {
            if (column.toLowerCase().compareTo(primaryKey.toLowerCase()) == 0) {
                columnName = column;
                break;
            }
        }
        return DELETE_FROM + data.getTableName() + WHERE + columnName + CONDITION;
    }
}
