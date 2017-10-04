package com.dad.controller;


import com.dad.bean.Agent;
import com.dad.bean.Customer;

public class CustomerController {

    public static Customer customer;

    public static Customer getCustomer() {
        return customer;
    }

    public static void setCustomer(Customer newCustomer) {
        customer = newCustomer;
    }
}
