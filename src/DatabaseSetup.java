import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseSetup {

    public static void setupDatabase() {
        String url = "jdbc:sqlite:database/bank.db";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {

            // USERS TABLE
            stmt.execute("CREATE TABLE IF NOT EXISTS users (" +
                    "user_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "fullname TEXT NOT NULL," +
                    "username TEXT NOT NULL UNIQUE," +
                    "gender CHAR," +
                    "password TEXT NOT NULL" +
                    ");");

            // ACCOUNTS TABLE
            stmt.execute("CREATE TABLE IF NOT EXISTS accounts (" +
                    "user_id INTEGER NOT NULL," +
                    "accNumber TEXT NOT NULL UNIQUE," +
                    "balance DOUBLE DEFAULT 0," +
                    "FOREIGN KEY(user_id) REFERENCES users(user_id)" +
                    ");");

            // EMPLOYEES TABLE
            stmt.execute("CREATE TABLE IF NOT EXISTS employees (" +
                    "employee_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "fullname TEXT NOT NULL," +
                    "username TEXT NOT NULL UNIQUE," +
                    "gender CHAR," +
                    "password TEXT NOT NULL" +
                    ");");

            // EMPLOYEE SALARY TABLE
            stmt.execute("CREATE TABLE IF NOT EXISTS employees_salary (" +
                    "employee_id INTEGER NOT NULL," +
                    "salary DOUBLE DEFAULT 0," +
                    "FOREIGN KEY(employee_id) REFERENCES employees(employee_id)" +
                    ");");

            System.out.println("Database tables created successfully!");

        } catch (Exception e) {
            System.out.println("Error in setupDatabase(): " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        setupDatabase();
    }
}
