package exercise3;

/**
 * Created by Adrian.Purcaru on 07/07/2017.
 */
public class StudentGeniu extends Student {
    public StudentGeniu(String firstName, String lastName) {
        super(firstName, lastName);
    }
    
    @Override
    public int hashCode() {
        return getFirstName().hashCode();
    }
    
    @Override
    public boolean equals(Object obj) {
        if(this.getFirstName().equals(((Student) obj).getFirstName())){
            return true;
        }
        return false;
    }
}
