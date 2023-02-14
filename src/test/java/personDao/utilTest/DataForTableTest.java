package personDao.utilTest;


import org.junit.jupiter.api.Test;
import personDao.annotations.MyTable;
import bean.Person;
import personDao.util.DataForTable;
import util.CreatePerson;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class DataForTableTest {

    public static final String ID = "Id";
    public static final String NAME = "Name";
    public static final String SURNAME = "Surname";
    public static final String LOCAL_CLASS = "LocalClass";
    public static final String PERSON = "Person";
    public static final String KATE = "Kate";
    public static final String ANDERSON = "Anderson";

    @Test
    public void receiveTableName() {
        Person person = CreatePerson.create();
        DataForTable<Person> personData = new DataForTable<>(person);
        String actualName = personData.receiveTableName();
        String expectedName = PERSON;
        @MyTable(name = "LocalClass")
        class LocalInnerClass {
            private String name;
            private String surname;

            public LocalInnerClass(String name, String surname) {
                this.name = name;
                this.surname = surname;
            }
        }
        LocalInnerClass localClass = new LocalInnerClass(KATE, ANDERSON);
        DataForTable<LocalInnerClass> localClassData = new DataForTable<>(localClass);
        String anotherActualName = localClassData.receiveTableName();
        String anotherExpectedName = LOCAL_CLASS;
        assertEquals(actualName, expectedName);
        assertEquals(anotherActualName, anotherExpectedName);
    }

    @Test
    public void receiveFields() {
        Person person = CreatePerson.create();
        DataForTable<Person> personData = new DataForTable<>(person);
        Field[] actualArrayOfFields = personData.receiveFields();
        assertNotNull(actualArrayOfFields);
    }

    @Test
    public void receiveColumnsName() {
        Person person = CreatePerson.create();
        DataForTable<Person> personData = new DataForTable<>(person);
        List<String> actualList = personData.receiveColumnsName();
        List<String> expectedList = new ArrayList<>();
        Collections.addAll(expectedList, ID, NAME, SURNAME);
        assertEquals(actualList, expectedList);
    }
}