package personDao.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import personDao.annotations.MyColumn;
import personDao.annotations.MyTable;
import personDao.annotations.PrimaryKey;


/**
 * It is class DataForTable.
 * Lombok is used here.
 *
 * @author Scrum team.
 *
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
    private List<String> columns;
    private Field[] fields;
    private T object;
    private Field primaryKey;

    public DataForTable(T object) {
        this.object = object;
    }

    public String receiveTableName() {
        return object.getClass().getDeclaredAnnotation(MyTable.class).name();
    }

    public Field[] receiveFields() {
        return object.getClass().getDeclaredFields();
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
    public Field receivePrimaryKey(){
        Field[] fields = receiveFields();
        for (Field field :
                fields) {

            PrimaryKey primaryKey1 = field.getAnnotation(PrimaryKey.class);
            if(primaryKey1 != null){
                return field;
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
        this.setColumns(this.receiveColumnsName());
        this.setFields(this.receiveFields());
        this.setPrimaryKey(this.receivePrimaryKey());
    }
}
