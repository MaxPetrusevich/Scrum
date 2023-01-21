package Person;

import java.sql.SQLException;

public interface DAO<T> {
    T save(T t) throws SQLException, ClassNotFoundException, NoSuchMethodException;

    T get(int id) throws SQLException;

    void update(T t) throws SQLException;

    int delete(int id) throws SQLException;
}
