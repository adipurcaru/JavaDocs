package exercise3;

/**
 * Created by Adrian.Purcaru on 07/07/2017.
 */
public class StudentObisnuit extends Student{
    
    public StudentObisnuit(String firstName, String lastName) {
        super(firstName, lastName);
    }
    
    @Override
    public int hashCode() {
        int result = getFirstName().hashCode();
    
        result = 31 * result + getLastName().hashCode();
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
    
        Student stud = (Student) obj;
    
        if(stud.getFirstName().equals(((Student) obj).getFirstName())
                && stud.getLastName().equals(((Student) obj).getLastName()))
            return true;
        return false;
    }
}
