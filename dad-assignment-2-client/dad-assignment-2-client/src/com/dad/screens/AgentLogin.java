package com.dad.screens;

import com.dad.bean.Agent;
import com.dad.controller.AgentController;
import com.dad.polling.NewCustomerPoller;
import sun.rmi.runtime.Log;

import java.awt.*;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.rmi.RemoteException;
import java.util.Enumeration;
import java.util.Timer;


public class AgentLogin {

    private JFrame frame;
    private JTextField nameTF;
    private JPasswordField passwordTF;


    /**
     * Launch the application.
     */
    public static void NavigateToAgentLogInScreen() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AgentLogin window = new AgentLogin();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public AgentLogin() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 14));
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblName = new JLabel("Name");
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblName.setBounds(62, 75, 71, 29);
        frame.getContentPane().add(lblName);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblPassword.setBounds(62, 115, 71, 25);
        frame.getContentPane().add(lblPassword);

        nameTF = new JTextField();
        nameTF.setBounds(178, 81, 167, 20);
        frame.getContentPane().add(nameTF);
        nameTF.setColumns(10);

        passwordTF = new JPasswordField();
        passwordTF.setBounds(178, 119, 167, 21);
        frame.getContentPane().add(passwordTF);
        passwordTF.setColumns(10);

        JLabel lblAgentLogin = new JLabel("Agent Login");
        lblAgentLogin.setHorizontalAlignment(SwingConstants.CENTER);
        lblAgentLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblAgentLogin.setBounds(157, 33, 106, 20);
        frame.getContentPane().add(lblAgentLogin);

        JLabel lblFailedLogin = new JLabel("Invalid Crendentials, Please try again.");
        lblFailedLogin.setHorizontalAlignment(SwingConstants.CENTER);
        lblFailedLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblFailedLogin.setForeground(Color.red);
        lblFailedLogin.setBounds(157, 190, 200, 20);
        lblFailedLogin.setVisible(false);
        frame.getContentPane().add(lblFailedLogin);

		/*COMMENT
		 * Login Button to agent chat screen*/
        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // Get username get passowrd
                String username = nameTF.getText();
                String password = passwordTF.getText();

                // call RMI to authenticate agent
                try {
                    InetAddress agentLocalIP = InetAddress.getLocalHost();
                    InetAddress ipToUse = InetAddress.getByName(agentLocalIP.getHostAddress());

//                    URL whatismyip = null;
//
//                        whatismyip = new URL("http://checkip.amazonaws.com");
//                        BufferedReader in = new BufferedReader(new InputStreamReader(
//                                whatismyip.openStream()));
//                        String ip = in.readLine(); //you get the IP as a String
//
//                    InetAddress ipToUse = InetAddress.getByName(ip);


//                    System.out.println("New IP: " + getIpAddress());



                    System.out.println("This machine local ip address:" + agentLocalIP.getHostAddress());
                    Agent currentAgent = Main.authenticationDAO.agentLogin("joon", "joon123", ipToUse);

                    // Invalid credentials
                    if(currentAgent== null){
                        System.out.println("Failed login. Try again");
                        lblFailedLogin.setVisible(true);
                    }

                    // Agent is logged in and waiting for customer to join
                    AgentController.setAgent(currentAgent);
                    System.out.println("Success login. Agent name: " + AgentController.getAgent().getFirstName());

                    Timer timer = new Timer();
                    NewCustomerPoller newCustomerPoller = new NewCustomerPoller();
                    newCustomerPoller.setAgentPoller(Main.agentPoller);

                    timer.schedule(newCustomerPoller, 0, 1000);


                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                System.out.println("name: " + username + " password: " + password);

                AgentWaitingScreen.NavigateToAgentWaitingScreen();

                frame.setVisible(false);
            }
        });
        btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnLogin.setBounds(157, 172, 89, 23);
        frame.getContentPane().add(btnLogin);
    }
}
