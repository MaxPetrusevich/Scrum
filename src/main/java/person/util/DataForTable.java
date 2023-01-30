package person.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import lombok.*;
import person.annotations.*;

/**
 * It is class DataForTable.
 * Lombok is used here.
 *
 * @author Scrum team.
 * @version 2.1
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
public class DataForTable<T> {
    private String tableName;
    private Method[] methods;
    private List<String> columns;
    private Field[] fields;
    private T object;
    private String primaryKey;

    public DataForTable(T object) {
        this.object = object;
    }

    public String receiveTableName() {
        return object.getClass().getDeclaredAnnotation(MyTable.class).name();
    }

    public Field[] receiveFields() {
        return object.getClass().getDeclaredFields();
    }

    public Method[] receiveMethods() {
        return object.getClass().getMethods();
    }

    /**
     * method receiveGetters.
     *
     * @return getter.
     */
    public List<Method> receiveGetters() {
        List<Method> getters = new LinkedList<>();
        Method[] methods = getMethods();
        for (Method method :
                methods) {
            if (method.getName().compareTo("getId") == 0 || method
                .getName().compareTo("getClass") == 0) {
                continue;
            }
            if (method.getName().matches("get\\w*")) {
                getters.add(method);
            }
        }
        return getters;
    }

    /**
     * method receiveColumnsName.
     *
     * @return list.
     */
    public List<String> receiveColumnsName() {
        Field[] fields = receiveFields();
        List<String> list = new ArrayList<>();
        for (Field field :
                fields) {

            MyColumn myColumn = field.getAnnotation(MyColumn.class);
            list.add(myColumn.name());

        }
        return list;
    }

    /**
     * method receivePrimaryKey.
     *
     */
    public String receivePrimaryKey() {
        Field[] fields = receiveFields();
        for (Field field :
                fields) {

            PrimaryKey primaryKey1 = field.getAnnotation(PrimaryKey.class);
            if (primaryKey1 != null) {
                return primaryKey1.name();
            }
        }
        return null;
    }

    /**
     * method updateInfoInData.
     *
     */
    public void updateInfoInData() {
        this.setTableName(this.receiveTableName());
        this.setMethods(this.receiveMethods());
        this.setColumns(this.receiveColumnsName());
        this.setFields(this.receiveFields());
        this.setPrimaryKey(this.receivePrimaryKey());
    }
}
