public class Person {
    protected int ID ;
    private String fullName ;
    private String accountName ;
    private char gender ;
    private String password ;
    
    public Person( int ID , String fullName , String accountName , char gender ,String password ){
        this.ID = ID ;
        this.accountName = accountName ;
        this.fullName = fullName ;
        this.gender = gender ;
        this.password = password ;
        }
    public int getID() {
        return ID;
    }
    public String getAccountName() {
        return accountName;
    }
    public String getFullName() {
        return fullName;
    }
    public char getGender() {
        return gender;
    }
    public String getPassword() {
        return password;
    }
}