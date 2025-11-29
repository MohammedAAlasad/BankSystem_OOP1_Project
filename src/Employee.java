public class Employee extends Person {

    private double salary ;

    public Employee(int ID, String fullName, String accountName, char gender , String password) {
        super(ID, fullName, accountName, gender,password);
    }
    
    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }
    
    
}
