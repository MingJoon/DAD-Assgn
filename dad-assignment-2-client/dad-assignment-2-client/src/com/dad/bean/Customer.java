
package com.dad.bean;


import java.io.Serializable;
import java.net.InetAddress;


/**
 * Each customer has an Agent associated with it
 * Each customer also has a chat object
 * Chat object consists of messages sent between 2 parties
 */
public class Customer extends User implements Serializable {
    private Agent agentAssociate;


    public Customer(String firstName, String userId, InetAddress ipAddress, Agent agentAssociate) {
        super(firstName, userId, ipAddress);
        this.agentAssociate = agentAssociate;
    }

    public Customer() {
    }

    public Agent getAgentAssociate() {
        return this.agentAssociate;
    }

    public void setAgentAssociate(Agent agentAssociate) {
        this.agentAssociate = agentAssociate;
    }


}
