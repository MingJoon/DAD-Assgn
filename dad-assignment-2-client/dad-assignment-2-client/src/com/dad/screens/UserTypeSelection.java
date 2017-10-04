package com.dad.screens;

import java.awt.*;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class UserTypeSelection {

    private JFrame frame;
    private  static JButton btnClient;
    private  static JLabel lblNoAgent;

    /**
     * Launch the application.
     */
    public static void NavigateToUserSelection() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UserTypeSelection window = new UserTypeSelection();
                    window.frame.setVisible(true);

                    boolean agentAvailable = Main.authenticationDAO.checkAgentAvailability();



                    if(agentAvailable){
                        System.out.println("Agent is available");
                    }
                    else{
                        System.out.println("Agent is not available");
                        btnClient.setEnabled(false);
                        lblNoAgent.setVisible(true);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public UserTypeSelection() {
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
        
		/*COMMENT
		 * Agent Button to Agent Login screen*/
        JButton btnAgent = new JButton("Agent");
        btnAgent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                AgentLogin.NavigateToAgentLogInScreen();
                frame.setVisible(false);
            }
        });
        btnAgent.setBounds(146, 52, 128, 35);
        frame.getContentPane().add(btnAgent);
		
		/*COMMENT
		 * Client Button to client log
		 * in screen*/
        btnClient = new JButton("Client");
        btnClient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                ClientLogin.NavigateToClientLogInScreen();
                frame.setVisible(false);
            }
        });
        btnClient.setBounds(146, 115, 128, 35);
        frame.getContentPane().add(btnClient);

        lblNoAgent = new JLabel("No Available Agent");
        lblNoAgent.setHorizontalAlignment(SwingConstants.CENTER);
        lblNoAgent.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNoAgent.setForeground(Color.red);
        lblNoAgent.setBounds(146, 130, 200, 20);
        lblNoAgent.setVisible(false);
        frame.getContentPane().add(lblNoAgent);

    }

}
