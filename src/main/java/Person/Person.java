package Person;

import lombok.*;



@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@MyTable(name = "Person")
public class Person {
    @MyColumn(name = "Id")
    private Integer id;
    @MyColumn(name = "Name")
    private String name;
    @MyColumn(name = "Surname")
    private String surname;


}
