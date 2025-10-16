public class User extends Person {

    private double balance ; 

    public User(int ID, String fullName, String accountName, char gender ,String password) {
        super(ID, fullName, accountName, gender , password);
    }
      
    void withdraw(double amount) {
        if ( amount <= 0 ) {
            System.out.print("Invalid withdrawal amount. Please enter a number greater than 0. ");
        }
        else if (amount > balance) {
            System.out.print(" Insufficient funds. " );
        }
        else {
            balance = balance - amount ;
            System.out.print("Withdrawal successful. ");
        }
    }

    void deposit(double amount) {
        if ( amount <= 0 ) {
            System.out.println("Invalid deposit amount. Please enter a number greater than 0 ");
        }
        else {
            balance = balance + amount ;
            System.out.println("Deposit successful");
        }
    } 

    public void showStatus() {
        System.out.println("ID: " + getID());
        System.out.println("Full name: " + getFullName());
        System.out.println("gender: "+ getGender());
        System.out.println("Balalnce: "+getBalance());
    }
    public void showStatus_withoutBalance() {
        System.out.println("ID: " + getID());
        System.out.println("Full name: " + getFullName());
        System.out.println("gender: "+ getGender());
    }

    public double getBalance() {
        return balance;
    }

}
