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

    public void deleteUser(int userId) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getID() == userId) {
                users.remove(i);
                System.out.println("User " + userId + " deleted successfully.");
                return; // stop after deleting
            }
        }
        System.out.println("User not found: " + userId);
    }
    
    

    public void findUserById(int userId) {
        for (User u : users) {
            if (u.getID() == userId) {
                System.out.println("User found:");
                System.out.println(u); 
                return; 
            }
        }
        System.out.println("User not found: " + userId);
    }
    

    public void addEmployee(Employee emp){

    }

    public void deleteEmployee(int empId){
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getID() == empId) {
                employees.remove(i);
                System.out.println("Employee " + empId + " deleted successfully.");
                return; // stop after deleting
            }
        }
        System.out.println("Employee not found: " + empId);
    }

    public void findEmployeeById(int empId) {
        for (Employee e : employees) {
            if (e.getID() == empId) {
                System.out.println("Employee found:");
                System.out.println(e);
                return; 
            }
        }
        System.out.println("Employee not found: " + empId);
    }
    


    public int checkMenusInput() {
        while (true) {
            try {
                int inp = input.nextInt();
                input.nextLine(); 
                return inp;
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number:");
                input.nextLine();
            }
        }
    }
    


}
