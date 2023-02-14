package personDao.serviceTest;


import org.h2.tools.RunScript;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import bean.Person;
import personDao.dao.DaoPerson;
import personDao.service.DaoPersonImpl;
import personDao.util.DataForTable;
import personDao.util.MyConnection;
import util.CreatePerson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static personDao.h2Test.TestPerson.WAY_TO_SCRIPT;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DaoImplTest {
    public static final String JOHN = "John";
    public static final String SMITH = "Smith";
    public static final String MICHELE = "Michele";
    public static final String WILLIAMS = "Williams";
    private Connection conn;
    @BeforeAll
    public void init() throws SQLException, FileNotFoundException {
        conn =  MyConnection.getConnection();
        RunScript.execute(conn, new FileReader(WAY_TO_SCRIPT));
    }

    @AfterAll
    public void over() throws SQLException {
        conn.close();
    }



    @Test
    public void save() throws SQLException, ClassNotFoundException, NoSuchMethodException {
        Person person = CreatePerson.create();
        DataForTable<Person> personData = new DataForTable<>(person);
        DaoPerson personDao = new DaoPersonImpl(conn,personData);
        personData.updateInfoInData();
        Person savedPerson = personDao.save(person);
        assertEquals(savedPerson.getName(), JOHN);
        assertTrue(savedPerson.getId() > 0);
    }

    @Test
    public void get() throws SQLException, ClassNotFoundException, NoSuchMethodException {
        Person person = CreatePerson.create();
        DataForTable<Person> personData = new DataForTable<>(person);
        DaoPerson dao = new DaoPersonImpl(conn,personData);
        personData.updateInfoInData();
        person = dao.save(person);
        Person personFromDB = dao.get(person.getId());
        assertEquals(personFromDB.getSurname(), SMITH);
    }

    @Test
    public void update() throws SQLException, ClassNotFoundException, NoSuchMethodException {
        Person person = CreatePerson.create();
        DataForTable<Person> personData = new DataForTable<>(person);
        DaoPerson dao = new DaoPersonImpl(conn,personData);
        personData.updateInfoInData();
        person = dao.save(person);
        person.setName(MICHELE);
        person.setSurname(WILLIAMS);
        dao.update(person);
        Person personFromDB = dao.get(person.getId());
        assertEquals(personFromDB.getSurname(), WILLIAMS);
        assertEquals(personFromDB.getName(), MICHELE);
    }

    @Test
    public void delete() throws SQLException, ClassNotFoundException, NoSuchMethodException {
        Person person = CreatePerson.create();
        DataForTable<Person> personData = new DataForTable<>(person);
        DaoPerson dao = new DaoPersonImpl(conn,personData);
        personData.updateInfoInData();
        person = dao.save(person);
        dao.delete(person.getId());
        Person personFromDB = dao.get(person.getId());
        assertNull(personFromDB);
    }
}