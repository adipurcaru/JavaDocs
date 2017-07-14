package ro.teamnet.zth.api.em;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by Adrian.Purcaru on 13/07/2017.
 */
public interface EntityManager {
    <T> T findById(Class<T> entityClass, Long id) throws ClassNotFoundException;
    long getNextIdVal(String tableName, String columnIdName) throws ClassNotFoundException;

    <T> Object insert(T entity) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, SQLException;
    <T> List<T> findAll(Class<T> entityClass) throws ClassNotFoundException, SQLException, NoSuchFieldException, IllegalAccessException, InstantiationException;
	
    <T> T update(T entity) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, SQLException;
	void delete(Object entity) throws ClassNotFoundException, IllegalAccessException, NoSuchFieldException, SQLException;
    <T> List<T> findByParams(Class<T> entityClass, Map<String, Object> params) throws ClassNotFoundException, IllegalAccessException, NoSuchFieldException, SQLException, InstantiationException;
 
}
