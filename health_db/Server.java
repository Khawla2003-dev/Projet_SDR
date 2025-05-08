// Server.java
import HealthSystem.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;


public class Server {
    public static void main(String[] args) {
        try {
            ORB orb = ORB.init(args, null);
            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();

            HealthServiceImpl healthService = new HealthServiceImpl();
            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(healthService);
            HealthService href = HealthServiceHelper.narrow(ref);

            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            NameComponent path[] = ncRef.to_name("HealthService");
            ncRef.rebind(path, href);

            System.out.println("Serveur prêt et en attente...");
            orb.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
