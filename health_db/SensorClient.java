// // SensorClient.java
// import HealthSystem.*;
// import org.omg.CORBA.*;
// import org.omg.CosNaming.*;
// import java.util.Random;

// public class SensorClient {
//     public static void main(String[] args) {
//         try {
//             ORB orb = ORB.init(args, null);
//             org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
//             NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
//             HealthService healthService = HealthServiceHelper.narrow(ncRef.resolve_str("HealthService"));

//             Random rand = new Random();
//             String patientId = "Patient_" + rand.nextInt(100);

//             while (true) {
//                 float temp = 36 + rand.nextFloat() * 3;
//                 float hr = 60 + rand.nextInt(40);
//                 float spo2 = 90 + rand.nextInt(10);
//                 healthService.sendData(patientId, temp, hr, spo2);

//                 Thread.sleep(5000); // envoie toutes les 5s
//             }

//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }
// }

import HealthSystem.*;
import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import java.util.Scanner;

public class SensorClient {
    public static void main(String[] args) {
        try {
            // Initialisation ORB et résolution du service distant
            ORB orb = ORB.init(args, null);
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            HealthService healthService = HealthServiceHelper.narrow(ncRef.resolve_str("HealthService"));

            Scanner scanner = new Scanner(System.in);
            System.out.println("=== Envoi manuel des données médicales ===");

            while (true) {
                System.out.print("ID patient : ");
                String patientId = scanner.nextLine();

                System.out.print("Température : ");
                float temperature = Float.parseFloat(scanner.nextLine());

                System.out.print("Fréquence cardiaque : ");
                float heartRate = Float.parseFloat(scanner.nextLine());

                System.out.print("SpO₂ : ");
                float spo2 = Float.parseFloat(scanner.nextLine());

                healthService.sendData(patientId, temperature, heartRate, spo2);

                System.out.println("✅ Données envoyées !");
                System.out.println("Souhaitez-vous saisir un autre patient ? (o/n) : ");
                String choix = scanner.nextLine();
                if (!choix.equalsIgnoreCase("o")) {
                    break;
                }
            }

            scanner.close();

        } catch (Exception e) {
            System.err.println("Erreur côté client : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
