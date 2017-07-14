package ro.teamnet.zth.appl.domain;

import ro.teamnet.zth.api.annotations.Table;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;

import java.util.Date;


/**
 * Created by Adrian.Purcaru on 14/07/2017.
 */
@Table(name="employees")
public class Employee {
    
    @Id(name = "employee_id")
    private Long employeeId;
    
    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "last_name")
    private String lastName;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "phone_number")
    private String phoneNumber;
    
    @Column(name = "hire_date")
    private Date hireDate;
    
    @Column(name = "job_id")
    private Job job;
    
    @Column(name = "salary")
    private Long salary;
    
    @Column(name = "commission_pct")
    private Double commissionPct;
    
    @Column(name = "manager_id")
    private Employee manager;
    
    @Column(name = "department_id")
    private Department department;
    
    public Long getEmployeeId() {
        return employeeId;
    }
    
    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public Date getHireDate() {
        return hireDate;
    }
    
    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }
    
    public Job getJob() {
        return job;
    }
    
    public void setJob(Job job) {
        this.job = job;
    }
    
    public Long getSalary() {
        return salary;
    }
    
    public void setSalary(Long salary) {
        this.salary = salary;
    }
    
    public Double getCommissionPct() {
        return commissionPct;
    }
    
    public void setCommissionPct(Double commissionPct) {
        this.commissionPct = commissionPct;
    }
    
    public Employee getManager() {
        return manager;
    }
    
    public void setManager(Employee manager) {
        this.manager = manager;
    }
    
    public Department getDepartment() {
        return department;
    }
    
    public void setDepartment(Department department) {
        this.department = department;
    }
    
    
    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", hireDate=" + hireDate +
                ", job=" + job +
                ", salary=" + salary +
                ", commissionPct=" + commissionPct +
                ", manager=" + manager +
                ", department=" + department +
                '}';
    }
    
}
