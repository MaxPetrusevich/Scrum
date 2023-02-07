package personDao.bean;

import org.junit.jupiter.api.Test;
import util.CreatePerson;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void testEquals() {
        Person person1 = CreatePerson.create();
        person1.setName("Test");
        assertNotEquals(person1, CreatePerson.create());
    }

    @Test
    void testHashCode() {
        Person person1 = CreatePerson.create();
        person1.setName("Test");
        assertNotEquals(person1, CreatePerson.create());
    }

    @Test
    void getId() {
        Person person = CreatePerson.create();
        person.setId(5);
        assertEquals(5, person.getId());
    }

    @Test
    void getName() {
        Person person = CreatePerson.create();
        assertEquals("John", person.getName());
    }

    @Test
    void getSurname() {
        Person person = CreatePerson.create();
        assertEquals("Smith", person.getSurname());

    }

    @Test
    void setId() {
        Person person = CreatePerson.create();
        person.setId(5);
        assertEquals(5, person.getId());

    }

    @Test
    void setName() {
        Person person = CreatePerson.create();
        person.setName("Test");
        assertEquals("Test", person.getName());
    }

    @Test
    void setSurname() {
        Person person = CreatePerson.create();
        person.setSurname("Test");
        assertEquals("Test", person.getSurname());

    }

    @Test
    void testToString() {
        assertInstanceOf(String.class, Person.builder().build().toString());
    }

    @Test
    void builder() {
        assertNotNull(Person.builder().build());
    }
    @Test
     void canEqual(){
        Person person = CreatePerson.create();
        assertTrue(person.canEqual(CreatePerson.create()));
     }
}