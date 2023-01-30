package person.serviceTest;

import org.junit.jupiter.api.Test;
import person.bean.Person;
import person.service.SqlQuery;
import person.util.DataForTable;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SqlQueryTest {
    private Connection conn;


    @Test
    public void getInsertQuery() {
        Person person = Person.builder().name("John").surname("Connor").build();
        DataForTable<Person> dataForTable = new DataForTable<>(person);
        dataForTable.updateInfoInData();
        String actualValue = SqlQuery.getInsertQuery(dataForTable);
        String expectedValue = "INSERT INTO Person (Name, Surname) Values (?,?)";
        assertEquals(actualValue, expectedValue);
    }

    @Test
    public void getSelectQuery() {
        Person person = Person.builder().name("John").surname("Connor").build();
        DataForTable<Person> dataForTable = new DataForTable<>(person);
        dataForTable.updateInfoInData();
        String actualValue = SqlQuery.getSelectQuery(dataForTable);
        String expectedValue = "select * from Person order by id";
        assertEquals(actualValue, expectedValue);
    }

    @Test
    public void getSelectByIdQuery() {
        Person person = Person.builder().name("John").surname("Connor").build();
        DataForTable<Person> dataForTable = new DataForTable<>(person);
        dataForTable.updateInfoInData();
        String actualValue = SqlQuery.getSelectByIdQuery(dataForTable);
        String expectedValue = "select * from Person where Id = ?";
        assertEquals(actualValue, expectedValue);
    }

    @Test
    public void getUpdateQuery() throws InvocationTargetException, IllegalAccessException {
        Person person = Person.builder().name("John").surname("Connor").build();
        DataForTable<Person> dataForTable = new DataForTable<>(person);
        dataForTable.updateInfoInData();
        String actualValue = SqlQuery.getUpdateQuery(dataForTable);
        String expectedValue = "update Person set Name = ?, Surname = ? where Id = ?";
        assertEquals(actualValue, expectedValue);
    }

    @Test
    public void getDeleteByIdQuery() {
        Person person = Person.builder().name("John").surname("Connor").build();
        DataForTable<Person> dataForTable = new DataForTable<>(person);
        dataForTable.updateInfoInData();
        String actualValue = SqlQuery.getDeleteByIdQuery(dataForTable);
        String expectedValue = "delete from Person where Id = ?";
        assertEquals(actualValue, expectedValue);
    }
}