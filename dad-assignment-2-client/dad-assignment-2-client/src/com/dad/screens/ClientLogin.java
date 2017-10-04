package com.dad.screens;

import com.dad.controller.CustomerController;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;


public class ClientLogin {

    private JFrame frame;
    private JTextField textField;

    /**
     * Launch the application.
     */
    public static void NavigateToClientLogInScreen() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ClientLogin window = new ClientLogin();
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
    public ClientLogin() {
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

        JLabel lblName = new JLabel("Name");
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblName.setBounds(59, 78, 81, 26);
        frame.getContentPane().add(lblName);

        textField = new JTextField();
        textField.setBounds(150, 83, 201, 20);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

		/*COMMENT
		 * Login Button to client chat screen*/
        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // Get client's IP Address and authenticate
                try {

                    InetAddress customerLocalIP = InetAddress.getLocalHost();
                    InetAddress ipToUse = InetAddress.getByName(customerLocalIP.getHostAddress());

                    System.out.println("Client ip from this machine: " + ipToUse);

                    // Get client first name
                    String clientFirstName = textField.getText();
                    CustomerController.setCustomer(Main.authenticationDAO.customerLogin(clientFirstName, ipToUse));


                } catch (UnknownHostException | RemoteException e1) {
                    e1.printStackTrace();
                }


                ClientChatScreen clientChat = new ClientChatScreen();
                clientChat.NavigateToClientChatScreen();
                frame.setVisible(false);
            }
        });
        btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnLogin.setBounds(150, 140, 89, 23);
        frame.getContentPane().add(btnLogin);

        JLabel lblClientLogin = new JLabel("Client Login");
        lblClientLogin.setHorizontalAlignment(SwingConstants.CENTER);
        lblClientLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblClientLogin.setBounds(150, 43, 113, 29);
        frame.getContentPane().add(lblClientLogin);
    }

}
