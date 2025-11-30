import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class Bank {

    Scanner input = new Scanner(System.in) ;


    ArrayList<User> users = new ArrayList<>() ;
    ArrayList<Employee> employees = new ArrayList<>() ;

        public void addEmployee(Employee emp) {
        String sql = "INSERT INTO employees(employee_id, fullname, username, gender, password) VALUES(?, ?, ?, ?, ?)";

        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, emp.getID());               // NATIONAL ID
            pstmt.setString(2, emp.getFullName());      // full name
            pstmt.setString(3, emp.getAccountName());      // username
            pstmt.setString(4, String.valueOf(emp.getGender())); // gender (convert char to string)
            pstmt.setString(5, emp.getPassword());      // password

            pstmt.executeUpdate();
            System.out.println("Employee added successfully!");

        } catch (Exception e) {
            System.out.println("Error adding employee: " + e.getMessage());
        }
    }
    public void deleteEmployee(int employeeId) {
    String sql = "DELETE FROM employees WHERE employee_id = ?";

    try (Connection conn = Database.connect();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setInt(1, employeeId);
        int rows = pstmt.executeUpdate();

        if (rows > 0)
            System.out.println("Employee deleted successfully!");
        else
            System.out.println("Employee not found!");

    } catch (Exception e) {
        System.out.println("Error deleting employee: " + e.getMessage());
    }
}
 public void addSalary(Employee emp) {
        String sql = "INSERT INTO employees_salary(employee_id, salary) VALUES(?, ?)";

        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, emp.getID());       // same employee_id
            pstmt.setDouble(2, emp.getSalary()); // salary value

            pstmt.executeUpdate();
            System.out.println("Salary added!");

        } catch (Exception e) {
            System.out.println("Error adding salary: " + e.getMessage());
        }
    }   
    public void deleteEmployeeSalary(int employeeId) {
    String sql = "DELETE FROM employees_salary WHERE employee_id = ?";

    try (Connection conn = Database.connect();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setInt(1, employeeId);
        pstmt.executeUpdate();

        System.out.println("Employee salary deleted!");

    } catch (Exception e) {
        System.out.println("Error deleting salary: " + e.getMessage());
    }
}



     public void addUser(User user) {
        String sql = "INSERT INTO users(user_id, fullname, username, gender, password) VALUES(?, ?, ?, ?, ?)";

        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, user.getID());               // NATIONAL ID
            pstmt.setString(2, user.getFullName());      // full name
            pstmt.setString(3, user.getAccountName());      // username
            pstmt.setString(4, String.valueOf(user.getGender())); // gender (convert char to string)
            pstmt.setString(5, user.getPassword());      // password

            pstmt.executeUpdate();
            System.out.println("user added successfully!");

        } catch (Exception e) {
            System.out.println("Error adding user: " + e.getMessage());
        }
    }
    
    public void deleteUser(int userId) {
    String sql = "DELETE FROM users WHERE user_id = ?";

    try (Connection conn = Database.connect();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setInt(1, userId);

        int rows = pstmt.executeUpdate();
        if (rows > 0)
            System.out.println("User deleted!");
        else
            System.out.println("User not found!");

    } catch (Exception e) {
        System.out.println("Error deleting user: " + e.getMessage());
    }
}
     public void addAcc(User user) {
        String sql = "INSERT INTO accounts(user_id, accNumber, balance) VALUES(?, ?, ?)";

        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, user.getID());               // NATIONAL ID
            pstmt.setString(2, user.getAccount().getAccNumber());      // full 
            pstmt.setDouble(3, user.getAccount().getBalance());

            

            pstmt.executeUpdate();
            System.out.println("acc added successfully!");

        } catch (Exception e) {
            System.out.println("Error adding acc: " + e.getMessage());
        }
    }

public void deleteAccount(int accountId) {
    String sql = "DELETE FROM accounts WHERE acc_id = ?";

    try (Connection conn = Database.connect();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setInt(1, accountId);

        int rows = pstmt.executeUpdate();
        if (rows > 0)
            System.out.println("Account deleted!");
        else
            System.out.println("Account not found!");

    } catch (Exception e) {
        System.out.println("Error deleting account: " + e.getMessage());
    }
}


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
    public boolean isUsernameTakenEmp(String userName) {
        for (Employee u : employees) {
            if (u.getAccountName().equals(userName)) {
                return true;
            }
        }
        return false;
    }

    public static boolean validateId(String id) {
        for (Character c : id.toCharArray()) {
            if (id == null || id.isEmpty()) {
                System.out.println(" ID cannot be empty.");
                return false;
            }
            else if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true; 
    }

    public static boolean validatePassword(String pass) {
        if (pass.isEmpty()) {
            System.out.println("❌ Please enter the password");
            return false;
        }
        else if (pass.length() < 8) {
            System.out.println("❌ The password must have at least eight characters");
            return false;
        }
        else if (!pass.matches("[A-Za-z0-9]+")) {
            System.out.println("❌ Invalid password: must contain only letters and digits");
            return false;
        }
        else {
            int digitCount = 0;
            for (char c : pass.toCharArray()) {
                if (Character.isDigit(c)) {
                    digitCount++;
                }
            }
            if (digitCount < 2) {
                System.out.println("❌ Invalid password: must contain at least one digit");
                return false;
            } else {
                return true;
            }
        }
    }




    public void addusers(User user) {
        users.add(user) ;
        System.out.println("User " + user.getFullName() + " added " );
    }
    public void addemployee(Employee emps) {
        employees.add(emps) ;
        System.out.println("Employee " + emps + " added to ");
    }


     public void creatAccount(User user , String accountNumber , double initialbalance ) {
        Accounts account = new Accounts(accountNumber , initialbalance);
        user.setAccount(account);
    }



    // public void deleteUser(int userId) {
    //     for (int i = 0; i < users.size(); i++) {
    //         if (users.get(i).getID() == userId) {
    //             users.remove(i);
    //             System.out.println("User " + userId + " deleted successfully.");
    //             return; // stop after deleting
    //         }
    //     }
    //     System.out.println("User not found: " + userId);
    // }
    // public void deleteEmployee(int empId){
    //     for (int i = 0; i < employees.size(); i++) {
    //         if (employees.get(i).getID() == empId) {
    //             employees.remove(i);
    //             System.out.println("Employee " + empId + " deleted successfully.");
    //             return; // stop after deleting
    //         }
    //     }
    //     System.out.println("Employee not found: " + empId);
    // 
    
    public boolean checkIfExisit(int userId) {
        for (User u : users) {
            if (u.getID() == userId) {
                return true; 
            }
        }
        return false ; 
    }
    public boolean checkIfExisitEmp(int empid) {
        for (Employee e : employees) {
            if (e.getID() == empid) {
                return true; 
            }
        }
        return false ; 
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

    public Accounts findEmployeeById(int empId) {
        for (Employee e : employees) {
            if (e.getID() == empId) {
                System.out.println("Employee found:");
                System.out.println(e);
                break ;
            }
        }
        System.out.println("Employee not found: " + empId);
        return null;
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

    public User loginSuccess(int idForLogin, String passForLogin)
    {
        for (User u : users) {

        if (u.getID() == idForLogin && u.getPassword().equals(passForLogin)){
            return u;
            }

        }
        return null;
    }
    
    public Employee loginSuccessEmp(int idForLogin, String passForLogin)
    {
        for (Employee e : employees) {

        if (e.getID() == idForLogin && e.getPassword().equals(passForLogin)){
            return e;
            }

        }
        return null;
    }
    
    public void withdraw(User user, double amount) {
    user.getAccount().withdraw(amount);
    }
    public void deposit(User user, double amount) {
        user.getAccount().deposit(amount);
    }
    public void showAccount(User user) {
    System.out.println("ID: " + user.getID());
    System.out.println("Full Name: " + user.getFullName());
    System.out.println("Current Balance: " + user.getAccount().getBalance());
}
public void showAccountByEmp(int user) {
    for(User u : users){
        if(user == u.getID()){
            System.out.println("ID: " +u.getID());
            System.out.println("Full name: " + u.getFullName());
            System.out.println("gender: " + u.getGender());
        }
        else{
            System.out.println("Invalid input. Please enter a correct ID:");
        }
    }

}
public void showAccountByAdmin(int user) {
    for(User u : users){
        if(user == u.getID()){
            System.out.println("ID: " +u.getID());
            System.out.println("Full name: " + u.getFullName());
            System.out.println("gender: " + u.getGender());
            System.out.println("Balance: " + u.getAccount().getBalance());
        }
        else{
            System.out.println("Invalid input. Please enter a correct ID:");
        }
    }

}
public void showEmpByAdmin(int emp) {
    for(Employee e : employees){
        if(emp == e.getID()){
            System.out.println("ID: " +e.getID());
            System.out.println("Full name: " + e.getFullName());
            System.out.println("gender: " + e.getGender());
            System.out.println("salary: " + e.getSalary());
        }
        else{
            System.out.println("Invalid input. Please enter a correct ID:");
        }
    }
}

   public void editsalary(int empid, double newsalary) {
    boolean found = false;

    if (newsalary <= 0) {
        System.out.println("Invalid salary amount.");
        return;
    }

    for (Employee e : employees) {
        if (e.getID() == empid) {
            e.setSalary(newsalary);
            System.out.println("Salary updated successfully.");
            found = true;
            break;
        }
    }

    
}

    public void showUserStat(int userId) {

    // 1. Get user data
    String userSql = "SELECT fullname, username, gender FROM users WHERE user_id = ?";
    // 2. Get balance data
    String balanceSql = "SELECT balance FROM accounts WHERE user_id = ?";

    try (Connection conn = Database.connect()) {

        // ---- Get User Info ----
        try (PreparedStatement userPstmt = conn.prepareStatement(userSql)) {
            userPstmt.setInt(1, userId);
            var userRs = userPstmt.executeQuery();

            if (!userRs.next()) {
                System.out.println("User not found!");
                return;
            }

            String fullname = userRs.getString("fullname");
            String username = userRs.getString("username");
            char gender = userRs.getString("gender").charAt(0);

            // ---- Get Balance ----
            double balance = 0;
            try (PreparedStatement balPstmt = conn.prepareStatement(balanceSql)) {
                balPstmt.setInt(1, userId);
                var balRs = balPstmt.executeQuery();

                if (balRs.next()) {
                    balance = balRs.getDouble("balance");
                }
            }

            // ---- Print Final User Status ----
            System.out.println("\n===== USER STATUS =====");
            System.out.println("User ID      : " + userId);
            System.out.println("Full Name    : " + fullname);
            System.out.println("Username     : " + username);
            System.out.println("Gender       : " + gender);
            System.out.println("Balance      : " + balance);
            System.out.println("========================\n");

        }

    } catch (Exception e) {
        System.out.println("Error showing user status: " + e.getMessage());
    }
}
public void showUserStatByEmp(int userId) {

    // 1. Get user data
    String userSql = "SELECT fullname, username, gender FROM users WHERE user_id = ?";
    // 2. Get balance data
    String balanceSql = "SELECT balance FROM accounts WHERE user_id = ?";

    try (Connection conn = Database.connect()) {

        // ---- Get User Info ----
        try (PreparedStatement userPstmt = conn.prepareStatement(userSql)) {
            userPstmt.setInt(1, userId);
            var userRs = userPstmt.executeQuery();

            if (!userRs.next()) {
                System.out.println("User not found!");
                return;
            }

            String fullname = userRs.getString("fullname");
            String username = userRs.getString("username");
            char gender = userRs.getString("gender").charAt(0);

            // ---- Get Balance ----
            double balance = 0;
            try (PreparedStatement balPstmt = conn.prepareStatement(balanceSql)) {
                balPstmt.setInt(1, userId);
                var balRs = balPstmt.executeQuery();

                if (balRs.next()) {
                    balance = balRs.getDouble("balance");
                }
            }

            // ---- Print Final User Status ----
            System.out.println("\n===== USER STATUS =====");
            System.out.println("User ID      : " + userId);
            System.out.println("Full Name    : " + fullname);
            System.out.println("Username     : " + username);
            System.out.println("Gender       : " + gender);
            System.out.println("========================\n");

        }

    } catch (Exception e) {
        System.out.println("Error showing user status: " + e.getMessage());
    }
}
public void showEmpStatByAdmin(int userId) {

    String userSql = "SELECT fullname, username, gender FROM employees WHERE employee_id = ?";
    String salarySql = "SELECT salary FROM employees_salary WHERE employee_id = ?";

    try (Connection conn = Database.connect()) {

        // ---- Get Employee Info ----
        try (PreparedStatement userPstmt = conn.prepareStatement(userSql)) {
            userPstmt.setInt(1, userId);
            var userRs = userPstmt.executeQuery();

            if (!userRs.next()) {
                System.out.println("employee not found!");
                return;
            }

            String fullname = userRs.getString("fullname");
            String username = userRs.getString("username");
            char gender = userRs.getString("gender").charAt(0);

            // ---- Get Salary ----
            double salary = 0;
            try (PreparedStatement salPstmt = conn.prepareStatement(salarySql)) {
                salPstmt.setInt(1, userId);
                var salRs = salPstmt.executeQuery();

                if (salRs.next()) {
                    salary = salRs.getDouble("salary");
                }
            }

            // ---- Print Final Employee Status ----
            System.out.println("\n===== EMPLOYEE STATUS =====");
            System.out.println("Employee ID : " + userId);
            System.out.println("Full Name   : " + fullname);
            System.out.println("Username    : " + username);
            System.out.println("Gender      : " + gender);
            System.out.println("Salary      : " + salary);
            System.out.println("============================\n");

        }

    } catch (Exception e) {
        System.out.println("Error showing user status: " + e.getMessage());
    }
}

public Employee logindbForEmp(int userId, String password) {
    String sql = "SELECT * FROM employees WHERE employee_id = ? AND password = ?";

    try (Connection conn = Database.connect();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setInt(1, userId);
        pstmt.setString(2, password);

        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            // User exists → login successful
            Employee u = new Employee(
                rs.getInt("employee_id"),
                rs.getString("fullname"),
                rs.getString("username"),
                rs.getString("gender").charAt(0),
                rs.getString("password")
            );
            return u;
        } else {
            // No user → login failed
            return null;
        }

    } catch (Exception e) {
        System.out.println("Login error: " + e.getMessage());
        return null;
    }
}

public User logindb(int userId, String password) {
    String sql = "SELECT * FROM users WHERE user_id = ? AND password = ?";

    try (Connection conn = Database.connect();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setInt(1, userId);
        pstmt.setString(2, password);

        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            User u = new User(
               rs.getInt("user_id"),
               rs.getString("fullname"),
               rs.getString("username"),
               rs.getString("gender").charAt(0),
               rs.getString("password")
            );

            // ****** ADD THIS BLOCK ******
            String accSql = "SELECT accNumber, balance FROM accounts WHERE user_id = ?";
            try (PreparedStatement accStmt = conn.prepareStatement(accSql)) {
                accStmt.setInt(1, userId);
                ResultSet accRs = accStmt.executeQuery();

                if (accRs.next()) {
                    String accNum = accRs.getString("accNumber");
                    double bal = accRs.getDouble("balance");

                    Accounts acc = new Accounts(accNum, bal);
                    u.setAccount(acc);
                }
            }
            // *****************************

            return u;
        } 
        else {
            return null;
        }

    } catch (Exception e) {
        System.out.println("Login error: " + e.getMessage());
        return null;
    }
}

public boolean updateBalance(int userId, double amount) {
    String sql = "UPDATE accounts SET balance = ? WHERE user_id = ?";

    try (Connection conn = Database.connect();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setDouble(1, amount); // First ?
        pstmt.setInt(2, userId);    // Second ?

        int rowsAffected = pstmt.executeUpdate();

        return rowsAffected > 0;

    } catch (Exception e) {
        System.out.println("Update error: " + e.getMessage());
        return false;
    }
}

public boolean updateSalary(int employeeId, double amount) {
    String sql = "UPDATE employees_salary SET salary = ? WHERE employee_id = ?";

    try (Connection conn = Database.connect();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setDouble(1, amount);     // First ?
        pstmt.setInt(2, employeeId);    // Second ?

        int rowsAffected = pstmt.executeUpdate();

        return rowsAffected > 0;

    } catch (Exception e) {
        System.out.println("Update error: " + e.getMessage());
        return false;
    }
}
public String generateAccNumber() {
    String sql = "SELECT COUNT(*) AS total FROM accounts";

    try (Connection conn = Database.connect();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        int count = rs.getInt("total") + 1;
        return "U" + count;

    } catch (Exception e) {
        System.out.println("Error generating account number: " + e.getMessage());
        return "U999999"; // fallback
    }
}


}
