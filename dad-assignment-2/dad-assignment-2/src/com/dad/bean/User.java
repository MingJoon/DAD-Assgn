package com.dad.bean;


import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Super class to both Customer and Agent
 */
public class User implements Serializable {

    private String firstName;
    private String userId;
    private InetAddress ipAddress;

    public User(String firstName, String userId, InetAddress ipAddress) {
        this.firstName = firstName;
        this.userId = userId;
        this.ipAddress = ipAddress;
    }

    public User(){

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public InetAddress getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(InetAddress ipAddress) {
        this.ipAddress = ipAddress;
    }
}
