package finalproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DatabaseConnect {
    
    static final String url = "jdbc:postgresql://localhost:5432/management";
    static final String username = "postgres";
    static final String password = "cse3";
    static Connection conn = null;
    
    public static Connection connDB() throws ClassNotFoundException {
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
        return conn;
    }
    
}
