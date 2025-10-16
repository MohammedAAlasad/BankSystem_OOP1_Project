import java.util.*;

public class Bank {

    Scanner input = new Scanner(System.in) ;


    ArrayList<User> users = new ArrayList<>() ;
    ArrayList<Employee> employees = new ArrayList<>() ;


    public boolean isIdTaken(int id) {
        for (User u : users) {
            if (u.getID() == id) {
                return true; 
            }
        }
        return false; 
    }

    public boolean checkGender(char gender){
        gender = Character.toUpperCase(gender) ; 
        if(gender == 'M' || gender == 'F'){
            return true;
        }
        return false ; 

    }

    public boolean isUsernameTaken(String userName) {
        for (User u : users) {
            if (u.getAccountName().equals(userName)) {
                return true;
            }
        }
        return false;
    }

    public void addUser(User user){

    }

    public void deleteUser(String userId){

    }

    public void findUserById(int userId){
        for (User u : users) {
            if (u.getID() == userId) {
            
            }
        }
    }

    public void addEmployee(Employee emp){

    }

    public void deleteEmployee(String empId){

    }

    public void findEmployeeById(int empId){
        for (Employee e : employees) {
            if (e.getID() == empId) {
            
            }
        }
    }


    public int check_Menus_Input() {
        int inp = 0 ;

        try {
            inp = input.nextInt();
            input.nextLine() ; 
            }
            catch (Exception e){
                System.out.println("Invalid input. Please enter a number.");
                input.nextLine();
            }
            return inp ; 
        }


}
