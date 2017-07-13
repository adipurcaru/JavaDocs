package ro.teamnet.zth.api.em;

import org.junit.Assert;
import org.junit.Test;
import ro.teamnet.zth.appl.domain.Department;
import ro.teamnet.zth.appl.domain.Location;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static ro.teamnet.zth.api.em.EntityUtils.getTableName;

/**
 * Created by Adrian.Purcaru on 13/07/2017.
 */
public class EntityManagerImplTest {
    
    @Test
    public void findById() throws ClassNotFoundException {
        EntityManagerImpl emi = new EntityManagerImpl();
        Department var = emi.findById(Department.class, (long) 90);
        assertEquals("Should not be null", "Executive", var.getDepartmentName());
    }
    
    @Test
    public void TestGetNextIdVal() throws ClassNotFoundException {
        EntityManagerImpl emi = new EntityManagerImpl();
        long var = emi.getNextIdVal(getTableName(Department.class), "department_id");
        Assert.assertNotEquals("Should not be -1", -1, var);
    }
    
    @Test
    public void TestInsert() throws ClassNotFoundException, IllegalAccessException, NoSuchFieldException, SQLException {
        Location l = new Location();
        l.setCity("My City");
        l.setId((long) 999);
        l.setPostalCode("999");
        l.setStateProvince("My STATE");
        l.setStreetAddress("STREETSTREET");
        
        EntityManagerImpl emi = new EntityManagerImpl();
        Location var =(Location) emi.insert(l);
        Assert.assertNotEquals("bla bla", "New Department", var);
    }
    
    @Test
    public void TestFindAll() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        EntityManagerImpl emi = new EntityManagerImpl();
        List<Location> list= emi.findAll(Location.class);
        
        Assert.assertNotEquals("bla bla", "New Department", list);
    }
    
    
}