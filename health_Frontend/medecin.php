<?php
require('connexion.php');

try {
    $stmt = $conn->query("SELECT * FROM vitals ORDER BY timestamp DESC LIMIT 10");

    while ($row = $stmt->fetch(PDO::FETCH_ASSOC)) {
        echo "<tr>";
        echo "<td>" . htmlspecialchars($row['patient_id']) . "</td>";
        echo "<td>" . htmlspecialchars($row['heart_rate']) . " bpm</td>";
        echo "<td>" . htmlspecialchars($row['spo2']) . " %</td>";
        echo "<td>" . htmlspecialchars($row['temperature']) . " °C</td>";
        echo "<td>" . htmlspecialchars($row['timestamp']) . "</td>";
        echo "</tr>";
    }
} catch (PDOException $e) {
    echo "<tr><td colspan='5'>Erreur : " . $e->getMessage() . "</td></tr>";
}
?>
