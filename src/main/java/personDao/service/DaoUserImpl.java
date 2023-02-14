package personDao.service;

import bean.User;
import lombok.AllArgsConstructor;
import personDao.dao.DaoUser;
import personDao.util.DataForTable;

import java.sql.Connection;
import java.sql.SQLException;

@AllArgsConstructor
public class DaoUserImpl extends DaoImpl<User> implements DaoUser {
    public DaoUserImpl(DataForTable<User> data) throws SQLException {
        super(data);
    }
    public DaoUserImpl(Connection conn, DataForTable<User> data){
        super(conn,data);
    }

}
