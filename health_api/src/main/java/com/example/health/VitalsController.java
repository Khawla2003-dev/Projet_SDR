package com.example.health;

import org.springframework.web.bind.annotation.*;
import java.sql.*;
import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class VitalsController {

    @GetMapping("/vitals")
    public List<Map<String, Object>> getVitals() {
        List<Map<String, Object>> data = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/health_db", "root", "")) {

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM vitals ORDER BY timestamp DESC LIMIT 10");

            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("patientId", rs.getString("patient_id"));
                row.put("temperature", rs.getFloat("temperature"));
                row.put("heartRate", rs.getFloat("heart_rate"));
                row.put("spo2", rs.getFloat("spo2"));
                row.put("timestamp", rs.getTimestamp("timestamp"));
                data.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }

    @PostMapping("/vitals")
    public void insertVitals(@RequestBody Map<String, Object> payload) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/health_db", "root", "")) {

            String sql = "INSERT INTO vitals (patient_id, temperature, heart_rate, spo2, timestamp) VALUES (?, ?, ?, ?, NOW())";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, (String) payload.get("patientId"));
            stmt.setFloat(2, ((Number) payload.get("temperature")).floatValue());
            stmt.setFloat(3, ((Number) payload.get("heartRate")).floatValue());
            stmt.setFloat(4, ((Number) payload.get("spo2")).floatValue());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
