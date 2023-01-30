package person.h2Test;




import org.h2.tools.RunScript;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import person.bean.Person;
import person.dao.Dao;
import person.service.DaoImpl;
import person.util.DataForTable;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestPerson {


    @Test
    public void shouldSaveAndGet() throws SQLException, ClassNotFoundException, NoSuchMethodException, FileNotFoundException {

        Connection conn = new H2ConnectionSupplier().getConnection();
        RunScript.execute(conn, new FileReader("src\\test\\resources\\testPerson.sql"));
        Person person1 = Person.builder().name("John").surname("Smith").build();
        DataForTable<Person> data = new DataForTable<>(person1);
        data.updateInfoInData();
        Dao<Person> daoPerson = new DaoImpl(conn,data);
        person1 = daoPerson.save(person1);
        assertEquals("John", person1.getName());
        assertEquals("Smith", person1.getSurname());
        conn.close();

    }
    @Test
    public void shouldUpdate() throws SQLException, ClassNotFoundException, NoSuchMethodException, FileNotFoundException {
        Connection conn = new H2ConnectionSupplier().getConnection();
        RunScript.execute(conn, new FileReader("src\\test\\resources\\testPerson.sql"));
        Person person1 = Person.builder().name("John").surname("Smith").build();
        DataForTable<Person> data = new DataForTable<>(person1);
        data.updateInfoInData();
        Dao<Person> daoPerson = new DaoImpl(conn,data);
        person1 = daoPerson.save(person1);
        int id = person1.getId();
        person1.setName("John II");
        daoPerson.update(person1);
        person1 = daoPerson.get(id);
        assertEquals("John II", person1.getName());
        conn.close();

    }
    @Test
    public void shouldDelete() throws SQLException, ClassNotFoundException, NoSuchMethodException, FileNotFoundException {
        Connection conn = new H2ConnectionSupplier().getConnection();
        RunScript.execute(conn, new FileReader("src\\test\\resources\\testPerson.sql"));
        Person person1 = Person.builder().name("John").surname("Smith").build();
        DataForTable<Person> data = new DataForTable<>(person1);
        data.updateInfoInData();
        Dao<Person> daoPerson = new DaoImpl(conn,data);
        person1 = daoPerson.save(person1);
        int rows = daoPerson.delete(person1.getId());
        assertEquals(1, rows);
        conn.close();
    }

}
