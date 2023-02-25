package personDao.service;

import personDao.util.DataForTable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
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
import static personDao.data.Constants.*;

public class SqlQuery {


    /**
     * It is getInsertQuery method.
     *
     * @param data from generic Class
     * @return insert.
     */
    public static String getInsertQuery(DataForTable<?> data) {
        String insert = INSERT_INTO + data.getTableName() + OPEN_BRACKET;
        Field primaryKey = data.getPrimaryKey();
        List<String> columnsName = data.getColumns();
        for (String column :
                columnsName) {
            if (!(column.toLowerCase().compareTo(primaryKey.getName().toLowerCase()) == 0)) {
                insert = insert.concat(column + COMMA);
            }
        }
        insert = insert.replaceFirst(REGEX_FOR_VALUES, CLOSE_BRACKET);
        insert = insert.concat(VALUES);
        for (String column :
                columnsName) {
            if (!(column.toLowerCase().compareTo(primaryKey.getName().toLowerCase()) == 0)) {
                insert = insert.concat(QUSTION);
            }
        }
        insert = insert.replaceFirst(REGEX_FOR_END_OF_QUERY, CLOSE_BRACKET);
        return insert;
    }

    /**
     * It is setValues method.
     */
    public static void setValues(PreparedStatement ps, DataForTable<?> data)
            throws IllegalAccessException, InvocationTargetException, SQLException {
        int valuesCount = 1;
        Field[] fields = data.getFields();
        Field primaryKey = data.getPrimaryKey();
        for (Field field :
                fields) {
            if (field.getName().toLowerCase().compareTo(primaryKey.getName().toLowerCase()) == 0) {
                continue;
            }
            field.setAccessible(true);
            setValue(data, ps, field, valuesCount);
            valuesCount++;
            field.setAccessible(false);
        }
    }


    public static void setValues(PreparedStatement ps, int id) throws SQLException {
        ps.setInt(1, id);
    }

    /**
     * It is viewSelectAllResult method.
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
        return String.join("", "select * from ", data.getTableName(), " order by id");
    }

    public static String getSelectLimitQuery(DataForTable<?> data) {
        return String.join("", "select * from ", data.getTableName(), " limit %s, %s");
    }

    public static String getCountQuery(DataForTable<?> data) {
        return String.join("", "select COUNT(id) from ", data.getTableName());
    }

    /**
     * It is getSelectByIdQuery method.
     *
     * @param data from generic class
     * @return select
     */
    public static String getSelectByIdQuery(DataForTable<?> data) {
        List<String> columns = data.getColumns();
        String columnName = null;
        Field primaryKey = data.getPrimaryKey();
        for (String column :
                columns) {
            if (column.toLowerCase().compareTo(primaryKey.getName().toLowerCase()) == 0) {
                columnName = column;
                break;
            }
        }
        return String.join("", SELECT_FROM, data.getTableName(), WHERE, columnName, CONDITION);
    }


    /**
     * It is getUpdateQuery method.
     *
     * @param data from generic class
     * @return update
     */
    public static String getUpdateQuery(DataForTable<?> data)
            throws IllegalAccessException, InvocationTargetException {
        String update = UPDATE + data.getTableName() + SET;
        List<String> columnsName = data.getColumns();
        Field primaryKey = data.getPrimaryKey();
        for (String column :
                columnsName) {
            if (!(column.toLowerCase().compareTo(primaryKey.getName().toLowerCase()) == 0)) {
                update = update.concat(column + SET_FOR_UPDATE);
            }
        }
        update = update.replaceFirst(REGEX_FOR_VALUES, CONDITION_FOR_UPDATE);
        Field[] fields = data.getFields();
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getName().toLowerCase().compareTo(primaryKey.getName().toLowerCase()) == 0) {
                update = update.replace(STAR, columnsName.get(i));
                break;
            }
        }
        return update;
    }

    /**
     * It is setUpdateValue method.
     */
    public static int setUpdateValue(DataForTable<?> data, PreparedStatement ps,
                                     Field field, int valuesCount)
            throws SQLException, IllegalAccessException, InvocationTargetException {
        Field primaryKey = data.getPrimaryKey();
        field.setAccessible(true);
        if (field.getName().toLowerCase().compareTo(primaryKey.getName().toLowerCase()) == 0) {
            int idSetNumber = data.getFields().length;
            ps.setInt(idSetNumber, (Integer) field.get(data.getObject()));
        } else {
            setValue(data, ps, field, valuesCount);
            valuesCount = valuesCount + 1;
        }
        field.setAccessible(false);
        return valuesCount;
    }


    /**
     * It is setValue method.
     */
    private static void setValue(DataForTable<?> data, PreparedStatement ps, Field field,
                                 int parameterIndex) throws SQLException,
            IllegalAccessException {
        Class<?> type = field.getType();
        System.out.println(type.getName());
        if (type == Integer.class || type == Byte.class || type == Short.class) {
            ps.setInt(parameterIndex, field.getInt(data.getObject()));
        } else if (type == Long.class) {
            ps.setLong(parameterIndex, field.getLong(data.getObject()));
        } else if (type.isPrimitive()) {
            ps.setBoolean(parameterIndex, field.getBoolean(data.getObject()));
        } else if (type == String.class || type == Character.class) {
            ps.setString(parameterIndex, (String) field.get(data.getObject()));
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
        Field primaryKey = data.getPrimaryKey();
        for (String column :
                columns) {
            if (column.toLowerCase().compareTo(primaryKey.getName().toLowerCase()) == 0) {
                columnName = column;
                break;
            }
        }
        return String.join("", DELETE_FROM, data.getTableName(), WHERE, columnName, CONDITION);
    }
}
