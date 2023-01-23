package Person;



import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;


public class DAOImplTest {
    private Person person = Person.builder().name("John").surname("Connor").build();
    private DataForTable<Person> personData = new DataForTable<>(person);

    @Test
    public void save() throws SQLException, ClassNotFoundException, NoSuchMethodException {
        DAOPerson personDao = new DAOPersonImpl(personData);
        personData.updateInfoInData();
        Person savedPerson = personDao.save(person);
        assertEquals(savedPerson.getName(), "John");
        assertTrue(savedPerson.getId() > 0);
    }

    @Test
    public void get() throws SQLException {
        DAOPerson dao = new DAOPersonImpl(personData);
        personData.updateInfoInData();
        Person personFromDB = dao.get(1);
        assertEquals(personFromDB.getSurname(), "Test");
    }

    @Test
    public void update() throws SQLException {
        DAOPerson dao = new DAOPersonImpl(personData);
        personData.updateInfoInData();
        person.setId(7);
        person.setName("Michele");
        person.setSurname("Williams");
        dao.update(person);
        Person personFromDB = dao.get(7);
        assertEquals(personFromDB.getSurname(), "Williams");
//        assertEquals(personFromDB.getName(), "Michele");
    }

    @Test
    public void delete() throws SQLException {
        DAOPerson dao = new DAOPersonImpl(personData);
        personData.updateInfoInData();
        dao.delete(8);
        Person personFromDB = dao.get(8);
        assertNull(personFromDB);
    }
}