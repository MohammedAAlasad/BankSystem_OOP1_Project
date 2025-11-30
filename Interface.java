import java.util.*;

public class Interface {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        DatabaseSetup.setupDatabase();

        Bank bankFunctions = new Bank();
        bankFunctions.loadUsersFromDB();    
        bankFunctions.loadEmployeesFromDB();


        int bank_menu_choice;
        int user_menu_choice, user_function_choice;
        int employee_menu_choice ;
        int admin_function_choice;

        char gender, genderByEmp , genderbyadmin  ;
        int enteredId, enteredIdebyemp , enteredIdebyAdmin;
        String fullname = "", password  = "", passwordbyadmin = "";
        String enteredUsername = "", enteredUsernamebyemp = "" , enteredUsernameByAdmin = "" , fullnamebyadmin = "";
        double initialbalance;

        Admin theAdmin = new Admin(11111111, "Abdullah almouqhim", "ABdullah", 'M', "Abdullah@123");

        // User fakeuser = new User(123456789, "ali omar", "Ali12345", 'M', "alipass123");
        // bankFunctions.addUser(fakeuser);
        // String accNum = bankFunctions.generateAccNumber();
        // Accounts fakeaccount = new Accounts(accNum, 20000);
        // fakeuser.setAccount(fakeaccount);
        // bankFunctions.addAcc(fakeuser);

        // Employee employee = new Employee(100100, "Mohammed Alasad", "MohaAlasad", 'M', "M1234");
        // bankFunctions.addEmployee(employee);
        // employee.setSalary(5000);
        // bankFunctions.addSalary(employee);

        System.out.println("\n\n\nWelcome to CS Bank System:");

        do {
            System.out.println("\nMain Menu:");
            System.out.println("1. User");
            System.out.println("2. Employee");
            System.out.println("3. Admin");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            bank_menu_choice = bankFunctions.checkMenusInput();

            switch (bank_menu_choice) {

                // USER MENU
                case 1:

                    do {
                        System.out.println("\nUser Menu:");
                        System.out.println("1. Login");
                        System.out.println("2. Create Account");
                        System.out.println("3. Back");

                        user_menu_choice = bankFunctions.checkMenusInput();

                        switch (user_menu_choice) {

                            // LOGIN
                            case 1:
                                System.out.println("Please Enter Your ID:");
                                int idForLogin = bankFunctions.checkMenusInput();

                                System.out.println("Please Enter Your Password:");
                                String passForLogin = input.nextLine();

                                User loggedInUser = bankFunctions.logindb(idForLogin, passForLogin);

                                if (loggedInUser != null) {

                                    do {
                                        System.out.println("\nAccount Menu:");
                                        System.out.println("1. Withdraw");
                                        System.out.println("2. Deposit");
                                        System.out.println("3. Show Account Status");
                                        System.out.println("4. Delete Account");
                                        System.out.println("5. Back");

                                        user_function_choice = bankFunctions.checkMenusInput();

                                        switch (user_function_choice) {

                                            case 1:
                                                System.out.println("HOW MUCH DO YOU WANT TO WITHDRAW?");
                                                double amount = input.nextDouble();
                                                input.nextLine();
                                                loggedInUser.getAccount().withdraw(amount);
                                                bankFunctions.updateBalance(loggedInUser.getID() , loggedInUser.getAccount().getBalance());

                                                break;

                                            case 2:
                                                System.out.println("HOW MUCH DO YOU WANT TO DEPOSIT?");
                                                amount = input.nextDouble();
                                                input.nextLine();
                                                loggedInUser.getAccount().deposit(amount);
                                                bankFunctions.updateBalance(loggedInUser.getID() , loggedInUser.getAccount().getBalance());
                                                break;

                                            case 3:
                                            
                                            bankFunctions.showUserStat(loggedInUser.getID());
                                            break;

                                            case 4:
                                                System.out.println("Enter The User ID to Delete");
                                                int deletedUserId = input.nextInt();
                                                input.nextLine();
                                                bankFunctions.deleteUser(deletedUserId);

                                                if (deletedUserId == loggedInUser.getID()) {
                                                    bankFunctions.deleteUser(deletedUserId);
                                                    bankFunctions.deleteAccount(deletedUserId);
                                                }
                                                break;

                                            case 5:
                                                break;

                                            default:
                                                System.out.println("Invalid input.");
                                        }

                                    } while (user_function_choice != 5);

                                } else {
                                    System.out.println("ID or password is incorrect.");
                                }

                                break;

                            // CREATE ACCOUNT
                            case 2:

                                int enteredIdTest;
                                while (true) {
                                    System.out.println("Please Enter User ID");

                                    if (!input.hasNextInt()) {
                                        System.out.println("Invalid input! Enter a number.");
                                        input.nextLine();
                                        continue;
                                    }

                                    enteredIdTest = input.nextInt();
                                    input.nextLine();

                                    if (enteredIdTest <= 0) {
                                        System.out.println("ID must be positive.");
                                        continue;
                                    }

                                    if (!bankFunctions.checkIfExisit(enteredIdTest)) {
                                        enteredId = enteredIdTest;
                                        break;
                                    } else {
                                        System.out.println("ID is taken!");
                                    }
                                }

                                System.out.println("Please Enter User Full Name:");
                                fullname = input.nextLine();

                                while (true) {
                                    System.out.print("Please enter gender (M/F): ");
                                    String g = input.nextLine().trim().toUpperCase();

                                    if (g.length() == 1 && bankFunctions.checkGender(g.charAt(0))) {
                                        gender = g.charAt(0);
                                        break;
                                    }
                                    System.out.println("Invalid gender!");
                                }

                                while (true) {
                                    System.out.println("Please Enter Username:");
                                    String uname = input.nextLine().trim();

                                    if (!bankFunctions.isUsernameTaken(uname)) {
                                        enteredUsername = uname;
                                        break;
                                    } else {
                                        System.out.println("Username taken!");
                                    }
                                }

                                System.out.println("Please Enter Password:");
                                String PassTest;
                                while (true) {
                                    PassTest = input.nextLine().trim();
                                    if (Bank.validatePassword(PassTest)) {
                                        password = PassTest;
                                        break;
                                    } else {
                                        System.out.println("Invalid password!");
                                    }
                                }

                                User u = new User(enteredId, fullname, enteredUsername, gender, password);
                                bankFunctions.addUser(u);

                                System.out.println("Please enter initial balance:");
                                initialbalance = input.nextDouble();
                                input.nextLine();
                                String accNum1 = bankFunctions.generateAccNumber();

                                Accounts a = new Accounts(accNum1, initialbalance);
                                u.setAccount(a);
                                bankFunctions.addAcc(u);

                                System.out.println("Account created successfully!");
                                break;

                            case 3:
                                break;

                            default:
                                System.out.println("Invalid input.");
                        }

                    } while (user_menu_choice != 3);

                    break;

               // EMPLOYEE MENU
                case 2:

    do {
        System.out.println("Please Enter Your ID:");
        int idForLogin = bankFunctions.checkMenusInput();
        System.out.println("Please Enter Your Password:");
        String passForLogin = input.nextLine();

        Employee loggedInEmp = bankFunctions.logindbForEmp(idForLogin, passForLogin);

        if (loggedInEmp != null) {

            do {

                System.out.println("\nEmployee Menu:");
                System.out.println("1. Create User Account");
                System.out.println("2. Show User Info");
                System.out.println("3. Delete User");
                System.out.println("4. Back");

                employee_menu_choice = bankFunctions.checkMenusInput();  // MISSING BEFORE

                switch (employee_menu_choice) {

                    case 1:
                        int enteredIdTest2;
                        while (true) {
                            System.out.println("Please Enter User ID");

                            if (!input.hasNextInt()) {
                                System.out.println("Invalid input!");
                                input.nextLine();
                                continue;
                            }

                            enteredIdTest2 = input.nextInt();
                            input.nextLine();

                            if (enteredIdTest2 <= 0) {
                                System.out.println("ID must be positive.");
                                continue;
                            }

                            if (!bankFunctions.checkIfExisit(enteredIdTest2)) {
                                enteredIdebyemp = enteredIdTest2;
                                break;
                            } else {
                                System.out.println("ID taken!");
                            }
                        }

                        System.out.println("Please Enter User Full Name:");
                        String enterednamebyemp = input.nextLine();

                        while (true) {
                            System.out.print("Gender (M/F): ");
                            String g = input.nextLine().trim().toUpperCase();

                            if (g.length() == 1 && bankFunctions.checkGender(g.charAt(0))) {
                                genderByEmp = g.charAt(0);
                                break;
                            }
                            System.out.println("Invalid gender!");
                        }

                        while (true) {
                            System.out.println("Username:");
                            String uname = input.nextLine().trim();

                            if (!bankFunctions.isUsernameTaken(uname)) {
                                enteredUsernamebyemp = uname;
                                break;
                            } else {
                                System.out.println("Username taken!");
                            }
                        }

                        System.out.println("Enter Password:");
                        String PassTest2;
                        String enteredpasswordbyemp;

                        while (true) {
                            PassTest2 = input.nextLine().trim();

                            if (Bank.validatePassword(PassTest2)) {
                                enteredpasswordbyemp = PassTest2;
                                break;
                            }
                            System.out.println("Invalid password!");
                        }

                        User newUser = new User(enteredIdebyemp, enterednamebyemp, enteredUsernamebyemp, genderByEmp, PassTest2);

                        bankFunctions.addUser(newUser);

                        System.out.println("Initial Balance:");
                        initialbalance = input.nextDouble();
                        input.nextLine();
                        String accNum1 = bankFunctions.generateAccNumber();

                        Accounts newAcc = new Accounts(accNum1,initialbalance);
                        newUser.setAccount(newAcc);
                        bankFunctions.addAcc(newUser);
                        System.out.println("Account created successfully!");
                        break;

                    case 2:
                        System.out.println("Enter User ID ");
                        int ShowUser = input.nextInt();
                        input.nextLine();
                        bankFunctions.showUserStatByEmp(ShowUser);
                        break;

                    case 3:
                        System.out.println("Enter The User ID to Delete");
                        int  deletedUserId= input.nextInt();
                        input.nextLine();
                        bankFunctions.deleteUser(deletedUserId);
                        bankFunctions.deleteAccount(deletedUserId);
                        break;

                    case 4:
                        break;

                    default:
                        System.out.println("Invalid input.");
                }

            } while (employee_menu_choice != 4);

        } else {
            System.out.println("Wrong ID or Password.");
        }

    } while (false); // employee loop ends after login process

    break;

                // ADMIN MENU
                case 3:
                    System.out.println("Please Enter Your ID:");
                    int idForLogin = bankFunctions.checkMenusInput();

                    System.out.println("Please Enter Your Password:");
                    String passForLogin = input.nextLine();

                    if (theAdmin.getID() == idForLogin && theAdmin.getPassword().equals(passForLogin)){
 do {
                        System.out.println("\nAdmin Menu:");
                        System.out.println("1. Create Employee Account");
                        System.out.println("2. Delete Employee Account");
                        System.out.println("3. Show User Status(with balance)");
                        System.out.println("4. Show Employee Status(with salary)");
                        System.out.println("5. Edit Employee salary");
                        System.out.println("6. Back");

                        admin_function_choice = bankFunctions.checkMenusInput();

                        switch (admin_function_choice) {
                            case 1:
                                int enteredIdTest;
                                while (true) {
                                    System.out.println("Please Employee ID");

                                    if (!input.hasNextInt()) {
                                        System.out.println("Invalid input! Enter a number.");
                                        input.nextLine();
                                        continue;
                                    }

                                    enteredIdTest = input.nextInt();
                                    input.nextLine();

                                    if (enteredIdTest <= 0) {
                                        System.out.println("ID must be positive.");
                                        continue;
                                    }

                                    if (!bankFunctions.checkIfExisitEmp(enteredIdTest)) {
                                        enteredIdebyAdmin = enteredIdTest;
                                        break;
                                    } else {
                                        System.out.println("ID is taken!");
                                    }
                                }

                                System.out.println("Please Enter Employee Full Name:");
                                fullnamebyadmin = input.nextLine();

                                while (true) {
                                    System.out.print("Please enter gender (M/F): ");
                                    String g = input.nextLine().trim().toUpperCase();

                                    if (g.length() == 1 && bankFunctions.checkGender(g.charAt(0))) {
                                        genderbyadmin = g.charAt(0);
                                        break;
                                    }
                                    System.out.println("Invalid gender!");
                                }

                                while (true) {
                                    System.out.println("Please Enter Username:");
                                    String uname = input.nextLine().trim();

                                    if (!bankFunctions.isUsernameTaken(uname)) {
                                        enteredUsernameByAdmin = uname;
                                        break;
                                    } else {
                                        System.out.println("Username taken!");
                                    }
                                }

                                System.out.println("Please Enter Password:");
                                String PassTest;
                                while (true) {
                                    PassTest = input.nextLine().trim();
                                    if (Bank.validatePassword(PassTest)) {
                                        passwordbyadmin = PassTest;
                                        break;
                                    } else {
                                        System.out.println("Invalid password!");
                                    }
                                }

                                Employee e = new Employee(enteredIdebyAdmin, fullnamebyadmin, enteredUsernameByAdmin, genderbyadmin, passwordbyadmin);
                                bankFunctions.addEmployee(e);

                                System.out.println("Please enter the salary:");
                                double salary = input.nextDouble();
                                input.nextLine();
                                e.setSalary(salary);
                                bankFunctions.addSalary(e);

                                    break;

                            case 2:
                                System.out.println("Enter The User ID to Delete");
                                int deletedUserId= input.nextInt();
                                input.nextLine();
                                bankFunctions.deleteEmployee(deletedUserId);
                                bankFunctions.deleteEmployeeSalary(deletedUserId);
                                break;

                            case 3:
                                System.out.println("Enter User ID ");
                                int showuser = input.nextInt();
                                input.nextLine();
                                bankFunctions.showUserStat(showuser);  
                                break;

                            case 4:
                                System.out.println("Enter Employee ID:");
                                int showemp = input.nextInt();
                                input.nextLine();
                                bankFunctions.showEmpStatByAdmin(showemp);
                                break;

                            case 5:
                                System.out.println("Enter Employee ID:");
                                int idforeditsalary = input.nextInt();

                                System.out.println("Enter the new salary :");
                                double newsalary = input.nextDouble();
                                // bankFunctions.editsalary(idforeditsalary, newsalary);
                                bankFunctions.updateSalary(idforeditsalary , newsalary);
                                break;

                            case 6:
                                break;

                            default:
                                System.out.println("Invalid input.");
                        }

                    } while (admin_function_choice != 6);
                    }
            
                    break;

                case 4:
                    break;

                default:
                    System.out.println("Invalid input.");
            }

        } while (bank_menu_choice != 4);

        System.out.println("Thank you! GoodBye");
    }
}
