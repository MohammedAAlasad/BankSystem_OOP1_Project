import java.util.*;

public class Interface {

    static Scanner input = new Scanner(System.in) ;


    public static void main(String[] args) {

        Bank bankFunctions = new Bank();

        int bank_menu_choice ; 
        int user_menu_choice , user_function_choice ;
        int employee_menu_choice , employee_function_choice  ;
        int admin_function_choice; 

        boolean gender ;
        int id ;
        String fullname , accname , password ; 



        
        System.out.println("Welcome to CS Bank System:");
        do{
            System.out.println("\nMain Menu:");
            System.out.println("1. User");
            System.out.println("2. Employee");
            System.out.println("3. Admin");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            bank_menu_choice = bankFunctions.check_Menus_Input() ;

            // اول صفحه (موظف او عميل او خروج) 
            switch (bank_menu_choice) {

                //عميل
                case 1 :

                // لوب صفحة العميل 
                do{
                    System.out.println("\nUser Menu:");
                    System.out.println("1. Login");
                    System.out.println("2. Create Account");
                    System.out.println("3. Back");
                    user_menu_choice = bankFunctions.check_Menus_Input() ;
                    switch (user_menu_choice) {

                        //دخول
                        case 1 :
                        
                        //لوب الحساب حق العميل
                        do{
                            System.out.println("\nAccount Menu:");
                            System.out.println("1. Withdraw");
                            System.out.println("2. Deposit");
                            System.out.println("3. Show Account Status");
                            System.out.println("4. Delete Account");
                            System.out.println("5. Back");

                            
                            user_function_choice = bankFunctions.check_Menus_Input() ;
                            switch (user_function_choice) {
                                // سحب
                                case 1 :
                                break;

                                //ايداع
                                case 2 :
                                break;

                                //كشف حساب
                                case 3 :
                                break;

                                //حذف حساب
                                case 4 : 
                                break ; 

                                //رجوع للخلف
                                case 5 :
                                break;

                                //ادخال غلط
                                default:
                            }
                        }while(user_function_choice != 5) ; 
                        break ;


                        //انشاء حساب
                        case 2 :

                                    
                        System.out.println(" Please Enter User ID ");
                        System.out.println();


                        System.out.println(" Please Enter User Full Name ");
                        System.out.println();


                        System.out.println(" Please Enter User Gender ");
                        System.out.println();


                        System.out.println(" Please Enter User Account name ");
                        System.out.println();

                    

                        System.out.println(" Please Enter User Password ");
                        System.out.println("1. A password must have at least eight characters.\n" +
                                        "2. A password consists of only letters and digits.\n" +
                                        "3. A password must contain at least two digits");
                        System.out.println();


                        
                        System.out.println("Account created successfully!");
                    
                        System.out.println("Please enter an initial balance to User account");

                        break ;

                        // رجوع للخلف
                        case 3 :
                        break; 

                        //ادخال غلط
                        default:
                    }

                }while(user_menu_choice != 3 ) ;

                break ;


                //موظف
                case 2 :
                        //لوب صفحة الموظف
                        do{
                            employee_menu_choice = bankFunctions.check_Menus_Input() ;
                            switch (employee_menu_choice) {
                                // تسجيل دخول
                                case 1 :
                                // لوب حساب الموظف
                                do{
                                    employee_function_choice = bankFunctions.check_Menus_Input() ;

                                    switch (employee_function_choice) {
                                        // انشاء حساب لعميل
                                        case 1 :
                                                                        
                                        System.out.println(" Please Enter User ID ");
                                        System.out.println();


                                        System.out.println(" Please Enter User Full Name ");
                                        System.out.println();


                                        System.out.println(" Please Enter User Gender ");
                                        System.out.println();


                                        System.out.println(" Please Enter User Account name ");
                                        System.out.println();

                                    

                                        System.out.println(" Please Enter User Password ");
                                        System.out.println("1. A password must have at least eight characters.\n" +
                                                        "2. A password consists of only letters and digits.\n" +
                                                        "3. A password must contain at least two digits");
                                        System.out.println();


                                        
                                        System.out.println("Account created successfully!");
                                    
                                        System.out.println("Please enter an initial balance to User account");
                                        break;

                                        //كشف معلومات العميل
                                        case 2 :
                                            
                                        break;

                                        //حذف حساب عميل
                                        case 3 :
                                        System.out.println(" Please Enter User ID ");
                                        System.out.println();
                
                                        System.out.println(" Please Enter Employee Password ");
                                        System.out.println();
                
                                        System.out.println(" Please Enter Employee password again for verification Password ");
                                        System.out.println();
                                        break;
                                        
                                        //رحوع للخلف
                                        case 4 :

                                        break;

                                        //ادخال غلط
                                        default:
                                        
                                    }
                                }while(employee_function_choice != 4 ) ; 
                                break;

                                case 2 :
                                break;

                                //ادخال غلط
                                default:
                            }
                        }while(employee_menu_choice != 2) ; 
                break; 


                //صفحة الادمن
                case 3 :
                System.out.println("\nAdmin Menu:");
                System.out.println("1. Create Employee Account");
                System.out.println("2. Delete Employee Account");
                System.out.println("3. Show User Status(with balance)");
                System.out.println("4. Show Employee Status");
                System.out.println("5. Back");
                    
                do{
                    admin_function_choice = bankFunctions.check_Menus_Input() ; 
                    switch (admin_function_choice) {
                        case 1:
                        System.out.println(" Please Enter Employee ID ");
                        System.out.println();


                        System.out.println(" Please Enter Employee Full Name ");
                        System.out.println();


                        System.out.println(" Please Enter Employee Gender ");
                        System.out.println();


                        System.out.println(" Please Enter Employee Account name ");
                        System.out.println();

                    

                        System.out.println(" Please Enter Employee Password ");
                        System.out.println("1. A password must have at least eight characters.\n" +
                                        "2. A password consists of only letters and digits.\n" +
                                        "3. A password must contain at least two digits");
                        System.out.println();


                        
                        System.out.println("Account created successfully!");
                        break;

                        case 2:
                        System.out.println(" Please Enter Employee ID ");
                        System.out.println();

                        System.out.println(" Please Enter admin Password ");
                        System.out.println();

                        System.out.println(" Please Enter admin password again for verification Password ");
                        System.out.println();
                        break;

                        case 3:
                        System.out.println(" Please Enter User ID ");
                        break;

                        case 4:
                        System.out.println(" Please Enter Employee ID ");
                        break;

                        case 5:
                        break;
                    
                        default:
                    }
                }while(admin_function_choice != 5 );


                
                break ; 

                // رجوع للخلف
                case 5 :
                break;


                //ادخال غلط
                default:

            }



        }while(bank_menu_choice != 4) ;
        System.out.println("Thank you! GoodBay");

    }
}
