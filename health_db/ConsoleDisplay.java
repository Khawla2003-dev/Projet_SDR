// ConsoleDisplay.java
import java.sql.*;

public class ConsoleDisplay {
    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/health_db", "root", "password");
            Statement stmt = conn.createStatement();

            while (true) {
                ResultSet rs = stmt.executeQuery("SELECT * FROM vitals ORDER BY timestamp DESC LIMIT 10");
                System.out.println("=== Dernières données reçues ===");
                while (rs.next()) {
                    System.out.println(rs.getString("patient_id") + " | Temp: " + rs.getFloat("temperature") +
                            " | HR: " + rs.getInt("heart_rate") + " | SpO2: " + rs.getInt("spo2") +
                            " | Time: " + rs.getTimestamp("timestamp"));
                }
                Thread.sleep(10000); // refresh toutes les 10s
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
