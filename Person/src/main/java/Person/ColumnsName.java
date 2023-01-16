package Person;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ColumnsName {
    public static List<String> getColumnsName(Field[] fields) {
        List<String> list = new ArrayList<>();
        for (Field field :
                fields) {

            MyColumn myColumn = field.getAnnotation(MyColumn.class);
            list.add(myColumn.name());

        }
        return list;
    }
}
