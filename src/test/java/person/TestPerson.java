package person;




import org.junit.jupiter.api.Test;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestPerson {


    @Test
    public void shouldSaveAndGet() throws SQLException, ClassNotFoundException, NoSuchMethodException {

        Connection conn = new H2ConnectionSupplier().getConnection();
        PreparedStatement ps = conn.prepareStatement("delete from person");
        ps.executeUpdate();
        ps.close();
        Person person1 = Person.builder().name("John").surname("Smith").build();
        DataForTable<Person> data = new DataForTable<>(new Person());
        data.updateInfoInData();
        Dao<Person> daoPerson = new DaoImpl(conn,data);
        person1 = daoPerson.save(person1);
        assertEquals("John", person1.getName());
        assertEquals("Smith", person1.getSurname());
        conn.close();



    }
    @Test
    public void shouldUpdate() throws SQLException, ClassNotFoundException, NoSuchMethodException {
        Connection conn = new H2ConnectionSupplier().getConnection();
        PreparedStatement ps = conn.prepareStatement("delete from person");
        ps.executeUpdate();
        ps.close();
        Person person1 = Person.builder().name("John").surname("Smith").build();
        DataForTable<Person> data = new DataForTable<>(new Person());
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
    public void shouldDelete() throws SQLException, ClassNotFoundException, NoSuchMethodException {
        Connection conn = new H2ConnectionSupplier().getConnection();
        PreparedStatement ps = conn.prepareStatement("delete from person");
        ps.executeUpdate();
        ps.close();
        Person person1 = Person.builder().name("John").surname("Smith").build();
        DataForTable<Person> data = new DataForTable<>(new Person());
        data.updateInfoInData();
        Dao<Person> daoPerson = new DaoImpl(conn,data);
        person1 = daoPerson.save(person1);
        int rows = daoPerson.delete(person1.getId());
        assertEquals(1, rows);
        conn.close();
    }

}
