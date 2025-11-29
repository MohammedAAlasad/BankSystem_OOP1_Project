import java.sql.Connection;
import java.sql.PreparedStatement;

public class Accounts {

  
    private String accNumber;
    private double balance;
    private int user_id ;
    

    public Accounts( String accNumber, double balance) {
        this.accNumber = accNumber;
        this.balance = balance;
    }

    // Getters
    public int getUserId(User u) {
        user_id = u.getID() ;
        return user_id; 
    }
    public String getAccNumber() { return accNumber; }
    public double getBalance() { return balance; }

    // Deposit
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid deposit amount.");
            return;
        }

        balance += amount;
        updateBalanceInDatabase();
        System.out.println("Deposit successful! New balance: " + balance);
    }

    // Withdraw
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount.");
            return;
        }

        if (amount > balance) {
            System.out.println("Insufficient funds.");
            return;
        }

        balance -= amount;
        updateBalanceInDatabase();
        System.out.println("Withdrawal successful! New balance: " + balance);
    }

    // Update balance in SQLite
    private void updateBalanceInDatabase() {
        String sql = "UPDATE users_Balance SET balance = ? WHERE user_id = ?";

        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDouble(1, balance);
            pstmt.setInt(2, user_id);
            pstmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error updating balance: " + e.getMessage());
        }
    }
    
   

}
