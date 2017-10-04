package com.dad.controller;


import com.dad.bean.Agent;
import com.dad.bean.Customer;
import com.dad.voice.CaptureVoiceThread;

import javax.sound.sampled.*;
import java.util.ArrayList;

public class AgentController {

    private static Agent agent;

    public static void setAgent(Agent authenticatedAgent){
        agent = authenticatedAgent;
    }

    public static Agent getAgent(){
        return agent;
    }

    public static void addCustomer(Customer customer){
        agent.getCustomerAssociates().add(customer);
    }

    public static ArrayList<Customer> getAgentCustomers(){ return agent.getCustomerAssociates(); }



}
