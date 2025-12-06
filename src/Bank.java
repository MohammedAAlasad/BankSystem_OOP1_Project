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

            pstmt.setInt(1, emp.getID());              
            pstmt.setString(2, emp.getFullName());      
            pstmt.setString(3, emp.getAccountName());      
            pstmt.setString(4, String.valueOf(emp.getGender())); 
            pstmt.setString(5, emp.getPassword());      

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

            pstmt.setInt(1, emp.getID());      
            pstmt.setDouble(2, emp.getSalary()); 

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

            pstmt.setInt(1, user.getID());               
            pstmt.setString(2, user.getFullName());    
            pstmt.setString(3, user.getAccountName());     
            pstmt.setString(4, String.valueOf(user.getGender())); 
            pstmt.setString(5, user.getPassword());     

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
    String sql = "DELETE FROM accounts WHERE user_id = ?";

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
    
public boolean checkGender(char gender){
        gender = Character.toUpperCase(gender) ; 
        if(gender == 'M' || gender == 'F'){
            return true;
        }
        return false ; 

    }

public boolean isUsernameTaken(String username) {
    String sql = "SELECT 1 FROM users WHERE username = ?";

    try (Connection conn = Database.connect();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();

        return rs.next();

    } catch (Exception e) {
        System.out.println("Error checking username: " + e.getMessage());
        return false; 
    }
}

public boolean isUsernameTakenEmp(String username) {
    String sql = "SELECT 1 FROM employees WHERE username = ?";

    try (Connection conn = Database.connect();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();

        return rs.next();

    } catch (Exception e) {
        System.out.println("Error checking username: " + e.getMessage());
        return false;
    }
}

public static boolean validatePassword(String pass) {
        if (pass.isEmpty()) {
            System.out.println("Please enter the password");
            return false;
        }
        else if (pass.length() < 8) {
            System.out.println("The password must have at least eight characters");
            return false;
        }
        else if (!pass.matches("[A-Za-z0-9]+")) {
            System.out.println("Invalid password: must contain only letters and digits");
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
                System.out.println("Invalid password: must contain at least one digit");
                return false;
            } else {
                return true;
            }
        }
    }

public boolean checkIfExisit(int userId) {
    String sql = "SELECT 1 FROM users WHERE user_id = ?";

    try (Connection conn = Database.connect();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, userId);
        ResultSet rs = stmt.executeQuery();

        return rs.next(); // true if found

    } catch (Exception e) {
        System.out.println("Error checking user: " + e.getMessage());
        return false;
    }
}

public boolean checkIfExisitEmp(int empId) {
    String sql = "SELECT 1 FROM employees WHERE employee_id = ?";

    try (Connection conn = Database.connect();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, empId);
        ResultSet rs = stmt.executeQuery();

        return rs.next();

    } catch (Exception e) {
        System.out.println("Error checking employee: " + e.getMessage());
        return false;
    }
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

        pstmt.setDouble(1, amount); 
        pstmt.setInt(2, userId);    

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

        pstmt.setDouble(1, amount);     
        pstmt.setInt(2, employeeId);    

        int rowsAffected = pstmt.executeUpdate();

        return rowsAffected > 0;

    } catch (Exception e) {
        System.out.println("Update error: " + e.getMessage());
        return false;
    }
}

public String generateAccNumber() {
    String sql = "SELECT accNumber FROM accounts ORDER BY accNumber DESC LIMIT 1";

    try (Connection conn = Database.connect();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        if (rs.next()) {
            String last = rs.getString("accNumber");
            int num = Integer.parseInt(last.substring(1)); 
            return "U" + (num + 1);
        } else {
            return "U1"; 
        }

    } catch (Exception e) {
        System.out.println("Error generating account number: " + e.getMessage());
        return "U999999";
    }
}

public void loadUsersFromDB() {
    String sql = "SELECT * FROM users";

    try (Connection conn = Database.connect();
         PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {

        while (rs.next()) {
            User user = new User(
                rs.getInt("user_id"),
                rs.getString("fullname"),
                rs.getString("username"),
                rs.getString("gender").charAt(0),
                rs.getString("password")
            );

            // Load account for the user
            String accSql = "SELECT accNumber, balance FROM accounts WHERE user_id = ?";
            try (PreparedStatement accStmt = conn.prepareStatement(accSql)) {
                accStmt.setInt(1, user.getID());
                ResultSet accRs = accStmt.executeQuery();
                if (accRs.next()) {
                    Accounts acc = new Accounts(
                        accRs.getString("accNumber"),
                        accRs.getDouble("balance")
                    );
                    user.setAccount(acc);
                }
            }

            users.add(user);
        }

    } catch (Exception e) {
        System.out.println("Error loading users: " + e.getMessage());
    }
}

public void loadEmployeesFromDB() {
    String sql = "SELECT * FROM employees";

    try (Connection conn = Database.connect();
         PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {

        while (rs.next()) {
            Employee emp = new Employee(
                rs.getInt("employee_id"),
                rs.getString("fullname"),
                rs.getString("username"),
                rs.getString("gender").charAt(0),
                rs.getString("password")
            );

            // Load salary
            String salSql = "SELECT salary FROM employees_salary WHERE employee_id = ?";
            try (PreparedStatement salStmt = conn.prepareStatement(salSql)) {
                salStmt.setInt(1, emp.getID());
                ResultSet salRs = salStmt.executeQuery();
                if (salRs.next()) {
                    emp.setSalary(salRs.getDouble("salary"));
                }
            }

            employees.add(emp);
        }

    } catch (Exception e) {
        System.out.println("Error loading employees: " + e.getMessage());
    }
}

}
