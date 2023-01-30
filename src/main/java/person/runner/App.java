package person.runner;

import java.sql.SQLException;
import person.bean.Person;
import person.dao.DaoPerson;
import person.service.DaoPersonImpl;
import person.util.DataForTable;

/**
 * It is Application DAO class.
 * where we realize the logic
 * of our app.
 *
 * @author Scrum team
 * @version 2.1
 */
public class App {

    /**
     * It is main method without args.
     * Using builder pattern we are trying
     * to realize all methods.
     */
    public static void main(String[] args) throws SQLException,
            ClassNotFoundException, NoSuchMethodException {
        Person person1 = Person.builder().name("Test").surname("Person").build();
        DataForTable<Person> data = new DataForTable<>(new Person());
        data.updateInfoInData();
        DaoPerson dao = new DaoPersonImpl(data);
        person1 = dao.save(person1);
        System.out.println("Element was saved " + person1);
        person1 = dao.get(person1.getId());
        System.out.println("Element was got " + person1);
        person1.setSurname("Person");
        person1.setName("Update");
        dao.update(person1);
        System.out.println("Element was updated");
        int rows = dao.delete(person1.getId());
        System.out.println("Element was deleted. Rows affected = " + rows);

    }
}
