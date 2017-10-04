package com.dad.screens;

import com.dad.bean.Agent;
import com.dad.bean.Customer;
import com.dad.controller.VoiceReceiverController;
import com.dad.dadRmi.AgentPollingRMI;
import com.dad.dadRmi.AuthenticationRMI;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Main {

    private JFrame frame;
    private TextField ipTextField;
    public static InetAddress userIpAddress;
    public static AuthenticationRMI authenticationDAO;
    public static AgentPollingRMI agentPoller;



    /**
     * Launch the application.
     */
    public static void main(String[] args) {




        VoiceReceiverController voiceReceiverController = new VoiceReceiverController();
        voiceReceiverController.startReceving();


        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Main window = new Main();
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
    public Main() {
        initialize();


    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        ipTextField = new TextField();
        ipTextField.setBounds(94, 84, 242, 52);
        frame.getContentPane().add(ipTextField);
        ipTextField.setColumns(10);

        JLabel lblPleaseEnterIp = new JLabel("Please enter IP Address");
        lblPleaseEnterIp.setHorizontalAlignment(SwingConstants.CENTER);
        lblPleaseEnterIp.setBounds(112, 28, 200, 50);
        frame.getContentPane().add(lblPleaseEnterIp);


		/*COMMENT
		 * OK Button to user type selection screen*/
        JButton btnOk = new JButton("OK");
        btnOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    // Define Ip address variable from user input
                    userIpAddress = InetAddress.getByName(ipTextField.getText());

                    Registry registry = LocateRegistry.getRegistry(ipTextField.getText(), 1099);

                    authenticationDAO = (AuthenticationRMI) registry.lookup("authenticationDAO");
                    agentPoller = (AgentPollingRMI) registry.lookup("agentPoller");


                    System.out.println("user IP: " + userIpAddress.toString());

                } catch (UnknownHostException | RemoteException | NotBoundException e1) {
                    e1.printStackTrace();
                }

                UserTypeSelection userSelection = new UserTypeSelection();
                userSelection.NavigateToUserSelection();
                frame.setVisible(false);
            }
        });
        btnOk.setBounds(173, 157, 89, 23);
        frame.getContentPane().add(btnOk);
    }
}
