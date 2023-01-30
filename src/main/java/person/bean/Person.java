package person.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import person.annotations.MyColumn;
import person.annotations.MyTable;
import person.annotations.PrimaryKey;

/**
 *  Physical class.
 *  a Person table corresponding to the database.
 *
 * @author Scrum team
 * @version 2.1
 */
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@MyTable(name = "Person")
public class Person {

    /**
     * person id.
     */
    @PrimaryKey(name = "Id")
    @MyColumn(name = "Id")
    private Integer id;

    /**
     * person name.
     */
    @MyColumn(name = "Name")
    private String name;

    /**
     * person surname.
     */
    @MyColumn(name = "Surname")
    private String surname;


}
