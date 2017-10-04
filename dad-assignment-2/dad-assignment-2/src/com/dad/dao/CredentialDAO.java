package com.dad.dao;

import com.dad.bean.Agent;
import com.dad.utility.UUIDUtility;

import java.io.File;
import java.io.FileNotFoundException;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class CredentialDAO {

    private static String getAgentPassword(String firstName) throws FileNotFoundException {
        String password = "";
        File credentialFile = new File("credentials/agent.txt");
        Scanner scanner = new Scanner(credentialFile);

        while(scanner.hasNext()) {
            String line = scanner.nextLine();
            StringTokenizer lineTokens = new StringTokenizer(line, "||");
            String retrievedFirstName = "";

            String retrievedPassword;
            for(retrievedPassword = ""; lineTokens.hasMoreTokens(); retrievedPassword = lineTokens.nextToken()) {
                retrievedFirstName = lineTokens.nextToken();
            }

            if(retrievedFirstName.equalsIgnoreCase(firstName)) {
                password = retrievedPassword;
                break;
            }
        }

        scanner.close();
        return password;
    }


    public static Agent authenticateAgent(String firstName, String enteredPassword) throws FileNotFoundException {
        String password = getAgentPassword(firstName);
        boolean isValid = enteredPassword.equalsIgnoreCase(password);
        if(!isValid) {
            return null;
        } else {
            Agent agent = new Agent();
            agent.setFirstName(firstName);
            agent.setUserId(UUIDUtility.generateClientId());
            return agent;
        }
    }
}
