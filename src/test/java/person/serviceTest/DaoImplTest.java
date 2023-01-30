package person.serviceTest;


import org.junit.jupiter.api.Test;
import person.bean.Person;
import person.dao.DaoPerson;
import person.service.DaoPersonImpl;
import person.util.DataForTable;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class DaoImplTest {


    @Test
    public void save() throws SQLException, ClassNotFoundException, NoSuchMethodException {
        Person person = Person.builder().name("John").surname("Connor").build();
        DataForTable<Person> personData = new DataForTable<>(person);
        DaoPerson personDao = new DaoPersonImpl(personData);
        personData.updateInfoInData();
        Person savedPerson = personDao.save(person);
        assertEquals(savedPerson.getName(), "John");
        assertTrue(savedPerson.getId() > 0);
    }

    @Test
    public void get() throws SQLException, ClassNotFoundException, NoSuchMethodException {
        Person person = Person.builder().name("John").surname("Connor").build();
        DataForTable<Person> personData = new DataForTable<>(person);
        DaoPerson dao = new DaoPersonImpl(personData);
        personData.updateInfoInData();
        person = dao.save(person);
        Person personFromDB = dao.get(person.getId());
        assertEquals(personFromDB.getSurname(), "Connor");
    }

    @Test
    public void update() throws SQLException, ClassNotFoundException, NoSuchMethodException {
        Person person = Person.builder().name("John").surname("Connor").build();
        DataForTable<Person> personData = new DataForTable<>(person);
        DaoPerson dao = new DaoPersonImpl(personData);
        personData.updateInfoInData();
        person = dao.save(person);
        person.setName("Michele");
        person.setSurname("Williams");
        dao.update(person);
        Person personFromDB = dao.get(person.getId());
        assertEquals(personFromDB.getSurname(), "Williams");
        assertEquals(personFromDB.getName(), "Michele");
    }

    @Test
    public void delete() throws SQLException, ClassNotFoundException, NoSuchMethodException {
        Person person = Person.builder().name("John").surname("Connor").build();
        DataForTable<Person> personData = new DataForTable<>(person);
        DaoPerson dao = new DaoPersonImpl(personData);
        personData.updateInfoInData();
        person = dao.save(person);
        dao.delete(person.getId());
        Person personFromDB = dao.get(person.getId());
        assertNull(personFromDB);
    }
}