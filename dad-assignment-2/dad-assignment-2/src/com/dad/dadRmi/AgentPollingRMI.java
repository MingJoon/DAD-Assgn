package com.dad.dadRmi;


import com.dad.bean.Customer;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface AgentPollingRMI extends Remote {

    public Customer checkNewCustomer(String agentId, ArrayList<Customer> connectedCustomers)  throws RemoteException;
}
