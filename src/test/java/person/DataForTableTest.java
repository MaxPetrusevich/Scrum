package person;

import org.junit.Test;
import person.bean.Person;
import person.tableAnnotations.MyTable;
import person.util.DataForTable;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class DataForTableTest {
    private Person person = Person.builder().name("John").surname("Connor").build();
    private DataForTable<Person> personData = new DataForTable<>(person);
    @Test
    public void receiveTableName() {
        String actualName = personData.receiveTableName();
        String expectedName = "Person";
        @MyTable(name = "LocalClass")
        class LocalInnerClass {
            private String name;
            private String surname;

            public LocalInnerClass(String name, String surname) {
                this.name = name;
                this.surname = surname;
            }
        }
        LocalInnerClass localClass = new LocalInnerClass("Kate", "Anderson");
        DataForTable<LocalInnerClass> localClassData = new DataForTable<>(localClass);
        String anotherActualName = localClassData.receiveTableName();
        String anotherExpectedName = "LocalClass";
        assertEquals(actualName, expectedName);
        assertEquals(anotherActualName, anotherExpectedName);
    }

    @Test
    public void receiveFields() {
        Field[] actualArrayOfFields = personData.receiveFields();
        Field[] expectedArrayOfFields = person.getClass().getDeclaredFields();
        assertEquals(actualArrayOfFields, expectedArrayOfFields);
    }

    @Test
    public void receiveMethods() {
        Method[] actualArrayOfMethods = personData.receiveMethods();
        Method[] expectedArrayOfMethods = person.getClass().getMethods();
        assertEquals(actualArrayOfMethods, expectedArrayOfMethods);
    }

    @Test
    public void receiveGetters() throws ClassNotFoundException {
        @MyTable(name = "LocalClass")
        class LocalInnerClass {
            private String name;
            private String surname;

            public LocalInnerClass(String name, String surname) {
                this.name = name;
                this.surname = surname;
            }

            public String getName() {
                return name;
            }

            public String getSurname() {
                return surname;
            }
        }
        Class<?> localInnerClass = Class.forName(LocalInnerClass.class.getName());
        Method[] methods = localInnerClass.getDeclaredMethods();
        List<String> actualList = getMethodNames(methods);
        assertEquals(2, actualList.size());
        assertTrue(actualList.containsAll(Arrays.asList("getName",
                "getSurname")));
    }

    private List<String> getMethodNames(Method[] methods) {
        List<String> methodNames = new ArrayList<>();
        for (Method method : methods)
            methodNames.add(method.getName());
        return methodNames;
    }

    @Test
    public void receiveColumnsName() {
        List<String> actualList = personData.receiveColumnsName();
        List<String> expectedList = new ArrayList<>();
        Collections.addAll(expectedList, "Id", "Name", "Surname");
        assertEquals(actualList, expectedList);
    }
}