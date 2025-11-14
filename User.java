public class User extends Person {

    private Accounts account;

    public User(int ID, String fullName, String accountName, char gender, String password) {
        super(ID, fullName, accountName, gender, password);
    }

    public Accounts getAccount() {
        return account;
    }

    public void setAccount(Accounts account) {
        this.account = account;
    }

    public void showStatus() {
        System.out.println("ID: " + getID());
        System.out.println("Full name: " + getFullName());
        System.out.println("gender: " + getGender());
        System.out.println("Balance: " + account.getBalance());
    }
}
