
public class Accounts {
   
    private String accNumber ; 
    private double balance ;

    public Accounts(String accNumber , double balance) {
        this.accNumber = accNumber ;
        this.balance = balance ;
    }
  
    public String getAccNumber() {
        return accNumber;
    }

    public double getBalance() {
        return balance;
    }
}
