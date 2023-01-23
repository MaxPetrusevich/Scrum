package Person;

import lombok.AllArgsConstructor;

import java.sql.*;


@AllArgsConstructor

public class DAOPersonImpl extends DAOImpl<Person> implements DAOPerson {

    public DAOPersonImpl(DataForTable<Person> data) throws SQLException {
        super(data);
    }
}