<?php
require('connexion.php');

try {
    // Vérifier si un ID patient est fourni
    $patient_id = isset($_GET['patient_id']) ? trim($_GET['patient_id']) : '';
    
    if (empty($patient_id)) {
        echo "<tr><td colspan='5'>Veuillez fournir un ID patient</td></tr>";
        exit;
    }
    
    // Filtrer par ID patient spécifique
    $stmt = $conn->prepare("SELECT * FROM vitals WHERE patient_id = ? ORDER BY timestamp DESC LIMIT 10");
    $stmt->execute([$patient_id]);
    
    $rowCount = 0;
    while ($row = $stmt->fetch(PDO::FETCH_ASSOC)) {
        echo "<tr>";
        echo "<td>" . htmlspecialchars($row['patient_id']) . "</td>";
        echo "<td>" . htmlspecialchars($row['heart_rate']) . " bpm</td>";
        echo "<td>" . htmlspecialchars($row['spo2']) . " %</td>";
        echo "<td>" . htmlspecialchars($row['temperature']) . " °C</td>";
        echo "<td>" . htmlspecialchars($row['timestamp']) . "</td>";
        echo "</tr>";
        $rowCount++;
    }
    
    // Si aucun résultat trouvé pour cet ID
    if ($rowCount === 0) {
        echo "<tr><td colspan='5'>Aucune donnée trouvée pour le patient ID: " . htmlspecialchars($patient_id) . "</td></tr>";
    }
    
} catch (PDOException $e) {
    echo "<tr><td colspan='5'>Erreur : " . $e->getMessage() . "</td></tr>";
}
?>

