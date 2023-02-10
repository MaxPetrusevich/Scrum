package personDao.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import lombok.AllArgsConstructor;
import personDao.dao.DaoPerson;
import personDao.util.DataForTable;
import personDao.bean.Person;

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
    public DaoPersonImpl(Connection conn, DataForTable<Person> data){
        super(conn,data);
    }

}