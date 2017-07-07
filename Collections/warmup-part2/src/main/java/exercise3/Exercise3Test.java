package exercise3;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Adrian.Purcaru on 07/07/2017.
 */
public class Exercise3Test {
    public static void main(String[] args) {
        Map<StudentGeniu,BigDecimal> map1 = new HashMap<StudentGeniu, BigDecimal>();
        Map<StudentProst,BigDecimal> map2 = new HashMap<StudentProst, BigDecimal>();
        Map<StudentRepetent,BigDecimal> map3 = new HashMap<StudentRepetent, BigDecimal>();
        Map<StudentObisnuit,BigDecimal> map4 = new HashMap<StudentObisnuit, BigDecimal>();
        
        map1.put(new StudentGeniu("Popescu","Marcel"),new BigDecimal(15));
        map1.put(new StudentGeniu("Ionescu","Ionut"),new BigDecimal(17));
        map1.put(new StudentGeniu("Petrescu","Gica"),new BigDecimal(13));
        map1.put(new StudentGeniu("Petrescu","Gicaa"),new BigDecimal(13));
        
        map2.put(new StudentProst("Nume1","Nume1"),new BigDecimal(20));
        map2.put(new StudentProst("Nume2","Nume2"),new BigDecimal(19));
        
        map3.put(new StudentRepetent("Stud", "stud"), new BigDecimal(25));
        map3.put(new StudentRepetent("Stud2", "stud2"), new BigDecimal(25));
        
        map4.put(new StudentObisnuit("Obisnuit1", "Obisnuit1"), new BigDecimal(5));
        map4.put(new StudentObisnuit("Obisnuit2", "Obisnuit2"), new BigDecimal(7));
    
        Iterator<StudentGeniu> it1 = map1.keySet().iterator();
        Iterator<StudentProst> it2 = map2.keySet().iterator();
        Iterator<StudentRepetent> it3 = map3.keySet().iterator();
        Iterator<StudentObisnuit> it4 = map4.keySet().iterator();
    
        System.out.println("studenti genii: ");
        while(it1.hasNext()){
            StudentGeniu student =  it1.next();
            System.out.println("FirstName: " + student.getFirstName() +", LastName: " + student.getLastName());
            
        }
    
        System.out.println("studenti prosti: ");
        while(it2.hasNext()){
            StudentProst student =  it2.next();
            System.out.println("FirstName: " + student.getFirstName() +", LastName: " + student.getLastName());
        
        }
    
        System.out.println("studenti repetenti: ");
        while(it3.hasNext()){
            StudentRepetent student =  it3.next();
            System.out.println("FirstName: " + student.getFirstName() +", LastName: " + student.getLastName());
        
        }
    
        System.out.println("studenti obisnuiti: ");
        while(it4.hasNext()){
            StudentObisnuit student =  it4.next();
            System.out.println("FirstName: " + student.getFirstName() +", LastName: " + student.getLastName());
        
        }
        
    }
}
