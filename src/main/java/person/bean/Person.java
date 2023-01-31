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
 * Physical class.
 * a Person table corresponding to the database.
 *
 * @author Scrum team.
 *
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
    @PrimaryKey(name = "Id")
    @MyColumn(name = "Id")
    private Integer id;
    @MyColumn(name = "Name")
    private String name;
    @MyColumn(name = "Surname")
    private String surname;


}
