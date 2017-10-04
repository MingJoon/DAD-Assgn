package com.dad.polling;


import com.dad.bean.Agent;
import com.dad.bean.Customer;
import com.dad.controller.AgentController;
import com.dad.controller.CustomerController;
import com.dad.dadRmi.AgentPollingRMI;
import com.dad.screens.AgentChatScreen;
import com.dad.screens.ClientChatScreen;
import com.dad.screens.UserTypeSelection;

import java.rmi.RemoteException;
import java.util.TimerTask;

public class NewCustomerPoller extends TimerTask {

    AgentPollingRMI agentPoller;
    Agent agent;

    public NewCustomerPoller(){
        this.agent = AgentController.getAgent();
    }

    public AgentPollingRMI getAgentPoller() {
        return agentPoller;
    }

    public void setAgentPoller(AgentPollingRMI agentPoller) {
        this.agentPoller = agentPoller;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    @Override
    public void run() {

        // Polling operation of checking new connected customer
        try {


            Customer newConnectedCustomer = agentPoller.checkNewCustomer(agent.getUserId(), agent.getCustomerAssociates());


            if(newConnectedCustomer != null){
                System.out.println("New Customer joined!");
                AgentController.addCustomer(newConnectedCustomer);

                System.out.println("Spawning agent chat screen");
                AgentChatScreen.NavigateToAgentChatScreen(newConnectedCustomer);


                System.out.println("Current customer size in agent: " + AgentController.getAgentCustomers().size());

            }



        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
