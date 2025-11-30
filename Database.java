import java.sql.Connection;
import java.sql.DriverManager;

public class Database {

    private static final String URL = "jdbc:sqlite:database/bank.db";

    public static Connection connect() {
        try {
            Class.forName("org.sqlite.JDBC");

            return DriverManager.getConnection(URL);

        } catch (Exception e) {
            System.out.println("DATABASE CONNECTION ERROR: " + e.getMessage());
            return null;
        }
    }
}
