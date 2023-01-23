package Person;


import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SQLQueryTest {
    private Connection conn;
    private Person person = Person.builder().name("John").surname("Connor").build();
    private DataForTable<Person> dataForTable = new DataForTable<>(person);

    @Test
    public void getInsertQuery() {
        dataForTable.updateInfoInData();
        String actualValue = SQLQuery.getInsertQuery(dataForTable);
        String expectedValue = "INSERT INTO Person (Name, Surname) Values (?,?)";
        assertEquals(actualValue, expectedValue);
    }

    @Test
    public void getSelectQuery() {
        dataForTable.updateInfoInData();
        String actualValue = SQLQuery.getSelectQuery(dataForTable);
        String expectedValue = "select * from Person order by id";
        assertEquals(actualValue, expectedValue);
    }

    @Test
    public void getSelectByIdQuery() {
        dataForTable.updateInfoInData();
        String actualValue = SQLQuery.getSelectByIdQuery(dataForTable);
        String expectedValue = "select * from Person where Id = ?";
        assertEquals(actualValue, expectedValue);
    }

    @Test
    public void getUpdateQuery() throws InvocationTargetException, IllegalAccessException {
        dataForTable.updateInfoInData();
        String actualValue = SQLQuery.getUpdateQuery(dataForTable);
        String expectedValue = "update Person set Name = ?, Surname = ? where Id = ?";
        assertEquals(actualValue, expectedValue);
    }

    @Test
    public void getDeleteByIdQuery() {
        dataForTable.updateInfoInData();
        String actualValue = SQLQuery.getDeleteByIdQuery(dataForTable);
        String expectedValue = "delete from Person where Id = ?";
        assertEquals(actualValue, expectedValue);
    }
}