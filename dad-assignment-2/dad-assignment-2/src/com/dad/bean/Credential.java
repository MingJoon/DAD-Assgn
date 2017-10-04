package com.dad.bean;

import java.io.Serializable;

/**
 * Credential object consists of firstName and password required for authentication
 */
public class Credential implements Serializable {
    private String firstName;
    private String password;

    public Credential() {
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
