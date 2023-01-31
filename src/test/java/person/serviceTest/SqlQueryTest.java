package person.serviceTest;

import org.junit.jupiter.api.Test;
import person.bean.Person;
import person.service.SqlQuery;
import person.util.DataForTable;
import util.CreatePerson;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SqlQueryTest {
    public static final String TEST_INSERT = "INSERT INTO Person (Name, Surname) Values (?,?)";
    public static final String TEST_SELECT = "select * from Person order by id";
    public static final String TEST_SELECT_BY_ID = "select * from Person where Id = ?";
    public static final String TEST_UPDATE = "update Person set Name = ?, Surname = ? where Id = ?";
    public static final String TEST_DELETE = "delete from Person where Id = ?";
    private Connection conn;


    @Test
    public void getInsertQuery() {
        Person person = CreatePerson.create();
        DataForTable<Person> dataForTable = new DataForTable<>(person);
        dataForTable.updateInfoInData();
        String actualValue = SqlQuery.getInsertQuery(dataForTable);
        String expectedValue = TEST_INSERT;
        assertEquals(actualValue, expectedValue);
    }

    @Test
    public void getSelectQuery() {
        Person person = CreatePerson.create();
        DataForTable<Person> dataForTable = new DataForTable<>(person);
        dataForTable.updateInfoInData();
        String actualValue = SqlQuery.getSelectQuery(dataForTable);
        String expectedValue = TEST_SELECT;
        assertEquals(actualValue, expectedValue);
    }

    @Test
    public void getSelectByIdQuery() {
        Person person = CreatePerson.create();
        DataForTable<Person> dataForTable = new DataForTable<>(person);
        dataForTable.updateInfoInData();
        String actualValue = SqlQuery.getSelectByIdQuery(dataForTable);
        String expectedValue = TEST_SELECT_BY_ID;
        assertEquals(actualValue, expectedValue);
    }

    @Test
    public void getUpdateQuery() throws InvocationTargetException, IllegalAccessException {
        Person person = CreatePerson.create();
        DataForTable<Person> dataForTable = new DataForTable<>(person);
        dataForTable.updateInfoInData();
        String actualValue = SqlQuery.getUpdateQuery(dataForTable);
        String expectedValue = TEST_UPDATE;
        assertEquals(actualValue, expectedValue);
    }

    @Test
    public void getDeleteByIdQuery() {
        Person person = CreatePerson.create();
        DataForTable<Person> dataForTable = new DataForTable<>(person);
        dataForTable.updateInfoInData();
        String actualValue = SqlQuery.getDeleteByIdQuery(dataForTable);
        String expectedValue = TEST_DELETE;
        assertEquals(actualValue, expectedValue);
    }
}