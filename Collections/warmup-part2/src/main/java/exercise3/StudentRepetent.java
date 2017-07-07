package exercise3;

/**
 * Created by Adrian.Purcaru on 07/07/2017.
 */
public class StudentRepetent extends Student {
    
    public StudentRepetent(String firstName, String lastName) {
        super(firstName, lastName);
    }
    
    @Override
    public int hashCode() {
        int result = getFirstName().hashCode();
        
        result = 31 * result + getLastName().hashCode();
        return result;
    }
    
    public boolean equals(Object o1, Object o2) {
        if(((Student)o1).getFirstName().equals(((Student)o2).getFirstName())){
            return true;
        }
        return false;
    }
}
