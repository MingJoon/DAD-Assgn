import com.dad.bean.Agent;
import com.dad.dadRmi.IAgentPollingRMI;
import com.dad.dadRmi.IAuthenticationRMI;



import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.*;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {

    public static void main(String[] args) throws RemoteException {


        /**
         * Set up RMI
         * Bind object of IAuthenticationRMI class to the RMI registry
         * This is needed for client side to invoke
         */
//        Registry registry = LocateRegistry.createRegistry(1099);
        System.setProperty("java.rmi.server.hostname","192.168.43.40");
        LocateRegistry.createRegistry(1099);

        IAuthenticationRMI authenticationDAO = new IAuthenticationRMI();
        IAgentPollingRMI agentPoller = new IAgentPollingRMI();



        try {
            Naming.rebind("authenticationDAO", authenticationDAO);
            Naming.rebind("agentPoller", agentPoller);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }



    }
}
