package person.service;

import java.sql.SQLException;
import lombok.AllArgsConstructor;
import person.dao.DaoPerson;
import person.util.DataForTable;
import person.bean.Person;

/**
 * It is class DaoPersonImpl.
 * which extends DaoImpl.
 * Has one additional method inside
 * public DaoPersonImp.
 *
 * @author Scrum team.
 *
 */
@AllArgsConstructor
public class DaoPersonImpl extends DaoImpl<Person> implements DaoPerson {

    public DaoPersonImpl(DataForTable<Person> data) throws SQLException {
        super(data);
    }
}