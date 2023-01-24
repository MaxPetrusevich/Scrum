package Person;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SQLQuery {


    public static final String ID = "id";

    public static String getInsertQuery(DataForTable<?> data) {
        String insert = "INSERT INTO " + data.getTableName() + " (";
        List<String> columnsName = data.getColumns();
        for (String column :
                columnsName) {
            if (!(column.toLowerCase().compareTo(ID) == 0)) {
                insert = insert.concat(column + ", ");
            }
        }
        insert = insert.replaceFirst("(, )$", ")");
        insert = insert.concat(" Values (");
        for (String column :
                columnsName) {
            if (!(column.toLowerCase().compareTo(ID) == 0)) {
                insert = insert.concat("?,");
            }
        }
        insert = insert.replaceFirst("(,)$", ")");
        return insert;
    }

    public static void setValues(PreparedStatement ps, DataForTable<?> data)
            throws IllegalAccessException, InvocationTargetException, SQLException {
        int valuesCount = 1;
        Field[] fields = data.getFields();
        List<Method> getters = data.receiveGetters();
        for (Field field :
                fields) {
            if (field.getName().toLowerCase().compareTo(ID) == 0) {
                continue;
            }
            Method getter = null;
            for (Method method :
                    getters) {
                if (method.getName().toLowerCase().compareTo("get" + field.getName().toLowerCase()) == 0) {
                    getter = method;
                    break;
                }
            }
            setValue(data, ps, field, getter, valuesCount);
            valuesCount++;
        }
    }


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

    public static String getSelectByIdQuery(DataForTable<?> data) {
        List<String> columns = data.getColumns();
        String columnName = null;
        for (String column :
                columns) {
            if (column.toLowerCase().matches(ID)) {
                columnName = column;
                break;
            }
        }
        return "select * from " + data.getTableName() + " where " + columnName + " = ?";
    }

    public static void setValues(PreparedStatement ps, int id) throws SQLException {
        ps.setInt(1, id);
    }

    public static String getUpdateQuery(DataForTable<?> data)
            throws IllegalAccessException, InvocationTargetException {
        String update = "update " + data.getTableName() + " set ";
        List<String> columnsName = data.getColumns();
        for (String column :
                columnsName) {
            if (!(column.toLowerCase().compareTo(ID) == 0)) {
                update = update.concat(column + " = ?, ");
            }
        }
        update = update.replaceFirst("(, )$", " where * = ?");
        Field[] fields = data.getFields();
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getName().toLowerCase().compareTo(ID) == 0) {
                update = update.replace("*", columnsName.get(i));
                break;
            }
        }
        return update;
    }

    public static int setUpdateValue(DataForTable<?> data, PreparedStatement ps, Field field, String column, int valuesCount)
            throws SQLException, IllegalAccessException, InvocationTargetException {
        Method[] methods = data.getMethods();
        if (field.getName().toLowerCase().compareTo(ID) == 0) {
            int idSetNumber = data.getFields().length;
            for (Method method :
                    methods) {
                if (method.getName().toLowerCase().compareTo("get" + field.getName()) == 0) {
                    ps.setInt(idSetNumber, (Integer) method.invoke(data.getObject()));
                }
            }
        } else {
            for (Method method :
                    methods) {
                if (method.getName().toLowerCase().compareTo("get" + field.getName()) == 0) {
                    setValue(data, ps, field, method, valuesCount);
                    valuesCount = valuesCount + 1;

                }
            }
        }
        return valuesCount;
    }


    private static void setValue(DataForTable<?> data, PreparedStatement ps, Field field, Method method, int parameterIndex) throws SQLException, IllegalAccessException, InvocationTargetException {
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

    public static String getDeleteByIdQuery(DataForTable<?> data) {
        List<String> columns = data.getColumns();
        String columnName = null;
        for (String column :
                columns) {
            if (column.toLowerCase().matches(ID)) {
                columnName = column;
                break;
            }
        }
        return "delete from " + data.getTableName() + " where " + columnName + " = ?";
    }


}
