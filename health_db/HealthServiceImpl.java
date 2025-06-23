// HealthServiceImpl.java
import HealthSystem.HealthServicePOA;
import java.sql.*;

public class HealthServiceImpl extends HealthServicePOA {
    private Connection connection;

    public HealthServiceImpl() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/health_db", "root", "");
            System.out.println("Connexion MySQL réussie !");
        } catch (Exception e) {
            System.err.println("Échec de la connexion MySQL : " + e.getMessage());
            e.printStackTrace();
        }
    }
    

    @Override
    public void sendData(String patientId, float temperature, float heartRate, float spo2) {
        System.out.println("Reçu : " + patientId + ", Temp: " + temperature + ", HR: " + heartRate + ", SpO2: " + spo2);

        if (connection == null) {
            System.err.println("Erreur : connexion SQL est null !");
            return;
        }

        try {
            String sql = "INSERT INTO vitals (patient_id, temperature, heart_rate, spo2, timestamp) VALUES (?, ?, ?, ?, NOW())";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, patientId);
            stmt.setFloat(2, temperature);
            stmt.setFloat(3, heartRate);
            stmt.setFloat(4, spo2);
            stmt.executeUpdate();

            System.out.println("Données insérées avec succès.");
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getMessage());
            e.printStackTrace();
        }
    }


}
