package exercise3;

/**
 * Created by Adrian.Purcaru on 07/07/2017.
 */
public class Student{
    private String firstName;
    private String lastName;
    
    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        lastName = lastName;
    }
}
