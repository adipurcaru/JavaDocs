package ro.teamnet.zth.appl.Dao;

import ro.teamnet.zth.api.em.EntityManager;
import ro.teamnet.zth.api.em.EntityManagerImpl;
import ro.teamnet.zth.appl.domain.Department;

import java.sql.SQLException;

/**
 * Created by Adrian.Purcaru on 14/07/2017.
 */
public class DepartmentDao {
    EntityManager em = new EntityManagerImpl();
    
    public Department findById(long id) throws ClassNotFoundException {
        return em.findById(Department.class,id);
    }
    
    public long getNextIdVal(String tableName, String columnName) throws ClassNotFoundException {
        return em.getNextIdVal(tableName, columnName);
    }
    
    public Department insert(Department entity) throws ClassNotFoundException, SQLException, IllegalAccessException, NoSuchFieldException {
        return (Department) em.insert(entity);
    }
    
    public void delete(Department entity) throws ClassNotFoundException, SQLException, NoSuchFieldException, IllegalAccessException {
        em.delete(entity);
    }
    
    public Department update(Department entity) throws ClassNotFoundException, SQLException, IllegalAccessException, NoSuchFieldException {
        return em.update(entity);
    }
    
}
