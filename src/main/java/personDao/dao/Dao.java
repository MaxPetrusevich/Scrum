package personDao.dao;

import java.sql.SQLException;
import java.util.List;

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

    List<T> selectAll() throws SQLException;

    List<T> selectLimit(int currentPage, int countRecords) throws SQLException;
    List<T> selectLimitOrder(int currentPage, int countRecords,String field) throws SQLException;

    Integer getNumberOfRows() throws SQLException;
}
