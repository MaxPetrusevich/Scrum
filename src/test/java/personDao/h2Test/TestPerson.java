package personDao.h2Test;




import org.h2.tools.RunScript;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import personDao.bean.Person;
import personDao.dao.Dao;
import personDao.service.DaoImpl;
import personDao.util.DataForTable;
import util.CreatePerson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestPerson {
    public static final String WAY_TO_SCRIPT = "src\\test\\resources\\testPerson.sql";
    private Connection conn;
    @BeforeAll
    public void init() throws SQLException, FileNotFoundException {
        conn = new H2ConnectionSupplier().getConnection();
        RunScript.execute(conn, new FileReader(WAY_TO_SCRIPT));
    }

    @AfterAll
    public void over() throws SQLException {
        conn.close();
    }



    @Test
    public void shouldSaveAndGet() throws SQLException, ClassNotFoundException, NoSuchMethodException, FileNotFoundException {


        Person person1 = CreatePerson.create();
        DataForTable<Person> data = new DataForTable<>(person1);
        data.updateInfoInData();
        Dao<Person> daoPerson = new DaoImpl(conn,data);
        person1 = daoPerson.save(person1);
        assertEquals("John", person1.getName());
        assertEquals("Smith", person1.getSurname());
    }
    @Test
    public void shouldUpdate() throws SQLException, ClassNotFoundException, NoSuchMethodException, FileNotFoundException {
        Person person1 = CreatePerson.create();
        DataForTable<Person> data = new DataForTable<>(person1);
        data.updateInfoInData();
        Dao<Person> daoPerson = new DaoImpl(conn,data);
        person1 = daoPerson.save(person1);
        int id = person1.getId();
        person1.setName("John II");
        daoPerson.update(person1);
        person1 = daoPerson.get(id);
        assertEquals("John II", person1.getName());

    }
    @Test
    public void shouldDelete() throws SQLException, ClassNotFoundException, NoSuchMethodException, FileNotFoundException {
        Person person1 = CreatePerson.create();
        DataForTable<Person> data = new DataForTable<>(person1);
        data.updateInfoInData();
        Dao<Person> daoPerson = new DaoImpl(conn,data);
        person1 = daoPerson.save(person1);
        int rows = daoPerson.delete(person1.getId());
        assertEquals(1, rows);
    }

}
