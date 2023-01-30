package person.dao;

import java.sql.SQLException;

/**
 * It is interface Dao with generic class.
 * Contains basic crud methods
 *
 * @author Scrum team
 * @version 2.1
 */
public interface Dao<T> {

    /**
     * insert object.
     */
    T save(T t) throws SQLException, ClassNotFoundException, NoSuchMethodException;

    /**
     * select object.
     */
    T get(int id) throws SQLException;

    /**
     * update object.
     */
    void update(T t) throws SQLException;

    /**
     * delete object.
     */
    int delete(int id) throws SQLException;
}
