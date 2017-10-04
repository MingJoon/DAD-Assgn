package com.dad.dadRmi;


import com.dad.bean.Agent;

import com.dad.bean.Customer;
import com.dad.controller.ChatSocketController;
import com.dad.dao.CredentialDAO;
import com.dad.utility.UUIDUtility;


import java.io.FileNotFoundException;
import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class IAuthenticationRMI extends UnicastRemoteObject implements AuthenticationRMI {

    public IAuthenticationRMI() throws RemoteException{
        super();
    }

    @Override
    public Agent agentLogin(String firstName, String enteredPassword, InetAddress ipAddress) throws FileNotFoundException, RemoteException{

        Agent newAgent = CredentialDAO.authenticateAgent(firstName, enteredPassword);

        System.out.println("Agent ip address: " + ipAddress.toString());

        newAgent.setIpAddress(ipAddress);
        newAgent.setUserId(UUIDUtility.generateClientId());
        ChatSocketController.addNewConnectedAgent(newAgent);

        return newAgent;
    }


    @Override
    public Customer customerLogin(String firstName, InetAddress ipAddress) throws RemoteException {
        int availableAgentIndex = ChatSocketController.getAvailableAgent();

        if(availableAgentIndex < 0){
            return null;
        }

        Agent availableAgent = ChatSocketController.getConnectedAgents().get(availableAgentIndex);

        Customer newCustomer = new Customer();
        newCustomer.setFirstName(firstName);
        newCustomer.setUserId(UUIDUtility.generateClientId());
        newCustomer.setAgentAssociate(availableAgent);
        newCustomer.setIpAddress(ipAddress);

        ChatSocketController.addCustomerToAgent(newCustomer, availableAgentIndex);
        System.out.println("Client ip address: " + ipAddress.toString());

        System.out.println("New Customer joined ");
        return newCustomer;
    }

    @Override
    public boolean checkAgentAvailability() throws RemoteException {
        int availableAgentIndex = ChatSocketController.getAvailableAgent();
        boolean isAgentAvailable = (availableAgentIndex > -1);

        return isAgentAvailable;
    }


}
