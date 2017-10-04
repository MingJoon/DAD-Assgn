//import com.dad.bean.Agent;
//import com.dad.controller.AgentController;
//import com.dad.dadRmi.AgentPollingRMI;
//import com.dad.dadRmi.AuthenticationRMI;
//import com.dad.polling.NewCustomerPoller;
//import com.dad.screens.Main;
//
//import java.awt.*;
//import java.io.FileNotFoundException;
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//import java.rmi.NotBoundException;
//import java.rmi.RemoteException;
//import java.rmi.registry.LocateRegistry;
//import java.rmi.registry.Registry;
//import java.util.Timer;
//
//public class Main {
//
//    public static void main(String[] args) {
//
//
//
////        try {
////            InetAddress ipAddress = InetAddress.getByName("localhost");
////            Registry registry = LocateRegistry.getRegistry("10.103.88.3", 1099);
////
////            // Get objects from RMI
////            AuthenticationRMI authenticationDAO = (AuthenticationRMI) registry.lookup("authenticationDAO");
////            AgentPollingRMI agentPoller = (AgentPollingRMI) registry.lookup("agentPoller");
////
////
////            Agent currentAgent = authenticationDAO.agentLogin("joon", "joon123", ipAddress);
////
////            if(currentAgent != null){
////                System.out.println("Agent success login");
////
////                AgentController.setAgent(currentAgent);
////
////                // Poll server to check for new customer
////                Timer timer = new Timer();
////                NewCustomerPoller newCustomerPoller = new NewCustomerPoller();
////                newCustomerPoller.setAgentPoller(agentPoller);
////
////                timer.schedule(newCustomerPoller, 0, 500);
////
////            } else {
////                System.out.println("Failt authenticating agent");
////            }
////
////            Agent connectedAgent = authenticationDAO.customerLogin("joon", ipAddress);
////
////            if(connectedAgent != null){
////                System.out.println("Customer success login");
////                System.out.println(connectedAgent.getUserId());
////
////            } else{
////                System.out.println("Fail authentication");
////            }
////
////
////
////
////
////
////
////        } catch (RemoteException | NotBoundException | FileNotFoundException | UnknownHostException e) {
////            e.printStackTrace();
////        }
//    }
//}
