package com.dad.dadRmi;


import com.dad.bean.Customer;
import com.dad.controller.ChatSocketController;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class IAgentPollingRMI extends UnicastRemoteObject implements AgentPollingRMI {

    public IAgentPollingRMI() throws RemoteException{
        super();
    }

    @Override
    public Customer checkNewCustomer(String agentId, ArrayList<Customer> connectedCustomers) throws RemoteException {
        int agentIndex = ChatSocketController.getAgentIndexFromList(agentId);
        ArrayList<Customer> customersInServer = ChatSocketController.getConnectedAgents()
                .get(agentIndex)
                .getCustomerAssociates();

        /**
         * If Agent in client side doesn't have a customer, return the first one
         * If Agent in client side already has 1, return the second one
         */

        boolean clientAgentHasCustomer = connectedCustomers.size() > 0;
        boolean serverAgentHasCustomer = customersInServer.size() > 0;

        // If client and server have no customer
        if(!clientAgentHasCustomer && !serverAgentHasCustomer){
            return null;
        }

        // If client agent has no customer but server has
        if(!clientAgentHasCustomer && serverAgentHasCustomer){
            System.out.println("Giving agent his first customer");
            return customersInServer.get(0);
        }


         if(connectedCustomers.size() == 1 && customersInServer.size() == 2){

            String connectedCustomerId = connectedCustomers.get(0).getUserId();
            String customerInServerId = customersInServer.get(0).getUserId();

             if(connectedCustomerId.equals(customerInServerId)){
                 System.out.println("Returning index 1");
                 return customersInServer.get(1);
             }


        }
        return null;


    }
}
