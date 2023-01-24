package person;


import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SqlQueryTest {
    private Connection conn;
    private Person person = Person.builder().name("John").surname("Connor").build();
    private DataForTable<Person> dataForTable = new DataForTable<>(person);

    @Test
    public void getInsertQuery() {
        dataForTable.updateInfoInData();
        String actualValue = SqlQuery.getInsertQuery(dataForTable);
        String expectedValue = "INSERT INTO Person (Name, Surname) Values (?,?)";
        assertEquals(actualValue, expectedValue);
    }

    @Test
    public void getSelectQuery() {
        dataForTable.updateInfoInData();
        String actualValue = SqlQuery.getSelectQuery(dataForTable);
        String expectedValue = "select * from Person order by id";
        assertEquals(actualValue, expectedValue);
    }

    @Test
    public void getSelectByIdQuery() {
        dataForTable.updateInfoInData();
        String actualValue = SqlQuery.getSelectByIdQuery(dataForTable);
        String expectedValue = "select * from Person where Id = ?";
        assertEquals(actualValue, expectedValue);
    }

    @Test
    public void getUpdateQuery() throws InvocationTargetException, IllegalAccessException {
        dataForTable.updateInfoInData();
        String actualValue = SqlQuery.getUpdateQuery(dataForTable);
        String expectedValue = "update Person set Name = ?, Surname = ? where Id = ?";
        assertEquals(actualValue, expectedValue);
    }

    @Test
    public void getDeleteByIdQuery() {
        dataForTable.updateInfoInData();
        String actualValue = SqlQuery.getDeleteByIdQuery(dataForTable);
        String expectedValue = "delete from Person where Id = ?";
        assertEquals(actualValue, expectedValue);
    }
}