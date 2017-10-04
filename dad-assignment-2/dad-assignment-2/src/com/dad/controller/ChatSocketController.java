package com.dad.controller;


import com.dad.bean.Agent;
import com.dad.bean.Customer;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class ChatSocketController {

    /**
     * Stores all sockets for connectedAgents
     * Any time a customer wishes to connect. Agent availability is checked
     * From this List.
     */
    private static ArrayList<Agent> connectedAgents = new ArrayList<Agent>();

    /**
     * Return currently connected agents
     * @return connectedAgents
     */
    public static ArrayList<Agent> getConnectedAgents() {
        return connectedAgents;
    }

    /**
     * Add a new connected Agent to list of connected agents
     */
    public static void addNewConnectedAgent(Agent newAgent){
        connectedAgents.add(newAgent);
    }

    /**
     * Finds available agent whose clientAssociates < 2 from WebSocketController
     * @return index of the next available agent in the collection
     * */
    public static int getAvailableAgent(){
        int availableAgentIndex = -1;
        for(int i=0; i< connectedAgents.size(); i++){
            Agent currentAgent = connectedAgents.get(i);
            if(currentAgent.getCustomerAssociates().size() < 2){
                availableAgentIndex = i;
                break;
            }
        }
        return availableAgentIndex;
    }

    /**
     * Add Customer to available agent in ArrayList
     * @param customer The customer object to add
     * @param agentIndex The index of agent in ArrayList
     * */
    public static void addCustomerToAgent(Customer customer, int agentIndex){
        connectedAgents.get(agentIndex)
                .getCustomerAssociates()
                .add(customer);
    }

    /**
     * Finds agent index from connectedAgents list using its userId
     * @param agentId The userId of the agent
     * @return agentIndex The index of agent in connectedAgents list
     * */
    public static int getAgentIndexFromList(String agentId){
        int agentIndex = -1;

        for(int i=0; i<connectedAgents.size(); i++){
            if(connectedAgents.get(i).getUserId().equals(agentId)){
                agentIndex = i;
                break;
            }
        }
        return agentIndex;
    }


    /**
     * Finds agent index from connectedAgents list using its customer associates userId
     * @param customerId The userId of the customer
     * @return agentIndex The index of agent in connectedAgents list
     * */
    public static int getAgentIndexByCustomerId(String customerId){
        int agentIndex = -1;



        for(int i=0; i<connectedAgents.size(); i++){

            ArrayList<Customer> customers = connectedAgents.get(i).getCustomerAssociates();
            for(Customer customer : customers){

                if(customer.getUserId().equals(customerId)){
                    agentIndex = i;
                    break;
                }
            }
        }
        return agentIndex;
    }


    public static int getCustomerIndexFromAgent(String customerId, Agent agent){
        int agentIndex = -1;
        ArrayList<Customer> customerAssociates = agent.getCustomerAssociates();

        for(int i=0; i<customerAssociates.size(); i++){
            if(customerAssociates.get(i).getUserId().equals(customerId)){
                agentIndex = i;
                break;
            }
        }

        return agentIndex;
    }


    /**
     * Remove customer from agent's customer associate list
     * @param agentIndex Index of agent in connected agent list
     * @param customerIndex  Index of customer to remove from agent
     * */
    public static void removeCustomerAssociateFromAgent(int customerIndex, int agentIndex){
        connectedAgents
                .get(agentIndex)
                .getCustomerAssociates()
                .remove(customerIndex);
    }







}
