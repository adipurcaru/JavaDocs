package ro.teamnet.zth.api.em;

import org.junit.Assert;
import org.junit.Test;
import ro.teamnet.zth.appl.domain.Department;
import ro.teamnet.zth.appl.domain.Employee;
import ro.teamnet.zth.appl.domain.Location;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    
    @Test
    public void TestUpdate() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        EntityManagerImpl emi = new EntityManagerImpl();

        Location l = new Location();
        l.setId((long) 3201);
        l.setCity("supersuper");
        l.setPostalCode("99999");
        l.setStateProvince("statemondialu");
        l.setStreetAddress("strstr");
        
        Location t = emi.update(l);
        
        Assert.assertEquals("bla bla", "supersuper", t.getCity());
    }
    
    @Test
    public void TestDelete() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        EntityManagerImpl emi = new EntityManagerImpl();
        
        Location l = new Location();
        l.setId((long) 3201);
        l.setCity("supersuper");
        l.setPostalCode("99999");
        l.setStateProvince("statestate");
        l.setStreetAddress("strstr");
        
        emi.delete(l);
        
        //Assert.assertNotEquals("bla bla", "blabla", l.getCity());
    }
    
    @Test
    public void TestFindByParams() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        EntityManagerImpl emi = new EntityManagerImpl();

        Map<String, Object>map = new HashMap<>();
        map.put("location_id", "1100");
    
        List<Location> list = emi.findByParams(Location.class, map);
        
        Assert.assertNotEquals("bla bla", "blabla", list.size());
    }
    
    @Test
    public void testGetEmployees() throws SQLException, ClassNotFoundException {
        EntityManagerImpl emi = new EntityManagerImpl();
        emi.getEmployees("str");
    }
    
    @Test
    public void testInsertAll() throws SQLException, ClassNotFoundException, IllegalAccessException,
            NoSuchFieldException,
            InstantiationException {
        EntityManagerImpl entityManager = new EntityManagerImpl();
        
        Employee employee1 = new Employee();
        employee1.setDepartment(new Department());
        employee1.setCommissionPct((double) 100F);
        employee1.setEmail("asdad");
        employee1.setEmployeeId(100L);
        employee1.setFirstName("aaa");
        employee1.setLastName("aaa1");
        employee1.setPhoneNumber("1234");
        
        Employee employee2 = new Employee();
        employee2.setDepartment(new Department());
        employee2.setCommissionPct((double) 100F);
        employee2.setEmail("asdad");
        employee2.setEmployeeId(100L);
        employee1.setFirstName("bbb");
        employee1.setLastName("bbb1");
        
        List<Employee> employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);
        
        entityManager.insertAll(employees);
    }
    
    
}