CREATE DATABASE health_db;

USE health_db;

CREATE TABLE vitals (
    id INT AUTO_INCREMENT PRIMARY KEY,
    patient_id VARCHAR(50),
    temperature FLOAT,
    heart_rate INT,
    spo2 INT,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
