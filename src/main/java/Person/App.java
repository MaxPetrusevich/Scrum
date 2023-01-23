package Person;


import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, NoSuchMethodException {
        Person person1 = Person.builder().name("Test").surname("Person").build();
        DataForTable<Person> data = new DataForTable<>(new Person());
        data.updateInfoInData();
        DAOPerson dao = new DAOPersonImpl(data);
        person1 = dao.save(person1);
        System.out.println("Element was saved " + person1);
        person1 = dao.get(4);
        System.out.println("Element was got " + person1);
        person1.setSurname("Test");
        person1.setName("Update");
        dao.update(person1);
        System.out.println("Element was updated");
        int rows = dao.delete(4);
        System.out.println("Element was deleted. Rows affected = " + rows);



    }
}
