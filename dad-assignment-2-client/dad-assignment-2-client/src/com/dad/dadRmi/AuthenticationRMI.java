package com.dad.dadRmi;


import com.dad.bean.Agent;
import com.dad.bean.Customer;

import java.io.FileNotFoundException;
import java.net.InetAddress;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AuthenticationRMI extends Remote {

    public Agent agentLogin(String firstName, String enteredPassword, InetAddress ipAddress) throws FileNotFoundException, RemoteException;

    public Customer customerLogin(String firstName, InetAddress ipAddress) throws RemoteException;

    public boolean checkAgentAvailability() throws RemoteException;

}
