package Person;

import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException, ClassNotFoundException, NoSuchMethodException {
        DAOPerson dao = new DAOPersonImpl();
        Person person1 = Person.builder().name("Test").surname("Person").build();
        dao.save(person1);
        Person person = dao.get(1);
        System.out.println(person);
        person.setName("NewTest");
        person.setSurname("NewPerson");
        dao.update(person);
        dao.delete(1);

    }
}
