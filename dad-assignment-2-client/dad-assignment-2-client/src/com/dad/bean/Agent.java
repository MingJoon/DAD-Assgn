
package com.dad.bean;

import com.dad.bean.Customer;
import com.dad.bean.User;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Each agent may have maximum 2 customerAssociates
 */
public class Agent extends User implements Serializable {
    private ArrayList<Customer> customerAssociates;

    public Agent(String firstName, String userId, InetAddress ipAddress) {
        super(firstName, userId, ipAddress);
    }

    public Agent() {
        this.customerAssociates = new ArrayList<Customer>();
    }

    public ArrayList<Customer> getCustomerAssociates() {
        return this.customerAssociates;
    }


}
