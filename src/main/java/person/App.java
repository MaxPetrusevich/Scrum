package person;

import java.sql.SQLException;

/**
 * It is class App
 * where we realize the logic
 * of our app.
 *
 * @author Scrum team.
 *
 */
public class App {

    /**
     * It is main method without args.
     * Using builder pattern wir trying
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
        person1.setSurname("Test");
        person1.setName("Update");
        dao.update(person1);
        System.out.println("Element was updated");
        int rows = dao.delete(person1.getId());
        System.out.println("Element was deleted. Rows affected = " + rows);

    }
}
