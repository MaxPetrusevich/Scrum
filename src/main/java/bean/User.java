package bean;

import lombok.*;
import personDao.annotations.MyColumn;
import personDao.annotations.MyTable;
import personDao.annotations.PrimaryKey;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@MyTable(name = "user")
public class User {
    @PrimaryKey(name = "Id")
    @MyColumn(name = "Id")
    private Integer id;
    @MyColumn(name = "Name")
    private String name;
    @MyColumn(name = "Email")
    private String email;
    @MyColumn(name = "Password")
    private String password;
    @MyColumn(name = "Role")
    private boolean role;
}
