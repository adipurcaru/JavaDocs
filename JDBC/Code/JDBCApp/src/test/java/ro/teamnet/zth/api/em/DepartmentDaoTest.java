package ro.teamnet.zth.api.em;

import org.junit.Assert;
import org.junit.Test;
import ro.teamnet.zth.appl.Dao.DepartmentDao;
import ro.teamnet.zth.appl.domain.Department;
import ro.teamnet.zth.appl.domain.Location;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static ro.teamnet.zth.api.em.EntityUtils.getTableName;

/**
 * Created by Adrian.Purcaru on 14/07/2017.
 */
public class DepartmentDaoTest {
    
    @Test
    public void daoFindByIdTest() throws ClassNotFoundException {
        DepartmentDao dao = new DepartmentDao();
        Department var = dao.findById((long) 90);
        assertEquals("Should not be null", "Executive", var.getDepartmentName());
    }
    
    @Test
    public void TestGetNextIdVal() throws ClassNotFoundException {
        DepartmentDao dao = new DepartmentDao();
        long var = dao.getNextIdVal(getTableName(Department.class), "department_id");
        Assert.assertNotEquals("Should not be -1", -1, var);
    }
    
    @Test
    public void TestInsert() throws ClassNotFoundException, IllegalAccessException, NoSuchFieldException, SQLException {
        Department d = new Department();
        d.setDepartmentName("new DEPARTMENT");
        d.setLocation(1700L);
    
        DepartmentDao dao = new DepartmentDao();
        Department var =(Department) dao.insert(d);
        Assert.assertNotEquals("bla bla", "New Department", var);
    }
    
    @Test
    public void TestUpdate() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        DepartmentDao dao = new DepartmentDao();
    
        Department d = new Department();
        d.setDepartmentName("new DEPARTMENT2");
        d.setLocation(1700L);
        d.setId(271L);
    
        Department t = dao.update(d);
        
        Assert.assertEquals("bla bla", "new DEPARTMENT2", t.getDepartmentName());
    }
    
    @Test
    public void TestDelete() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        DepartmentDao dao = new DepartmentDao();
        Department d = new Department();
        d.setId(272L);
        
        dao.delete(d);
        
        //Assert.assertNotEquals("bla bla", "blabla", l.getCity());
    }
    
    
}
