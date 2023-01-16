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
        Person person1 = Person.builder().name("Anton").surname("Sidorov").build();
        dao.save(person1);
        Person person = dao.get(8);
        System.out.println(person);
        person.setName("Petr");
        person.setSurname("Ivanov");
        dao.update(person);
        dao.delete(9);

    }
}
