package person;

import java.sql.SQLException;

/**
 * It is interface Dao with generic class
 * with basic method
 * such like: save,
 * get, update, delete.
 *
 * @author Scrum team.
 *
 */
public interface Dao<T> {
    T save(T t) throws SQLException, ClassNotFoundException, NoSuchMethodException;

    T get(int id) throws SQLException;

    void update(T t) throws SQLException;

    int delete(int id) throws SQLException;
}
