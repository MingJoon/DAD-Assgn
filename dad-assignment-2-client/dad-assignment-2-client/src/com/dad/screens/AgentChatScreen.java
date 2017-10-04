package com.dad.screens;

import com.dad.bean.Customer;
import com.dad.controller.AgentController;
import com.dad.controller.CustomerController;
import com.dad.controller.VoiceController;
import com.dad.controller.VoiceReceiverController;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;


public class AgentChatScreen {

    private JFrame frame;
    private VoiceController voiceController;
    private Customer connectedCustomer;


    /**
     * Launch the application.
     */
    public static void NavigateToAgentChatScreen(Customer connectedCustomer) {
        System.out.println("Inside navigating agent chat screen");
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    System.out.println("Inside run navigate agent chat screen");
                    AgentChatScreen window = new AgentChatScreen(connectedCustomer);
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
    public AgentChatScreen(Customer connectedCustomer) {
        this.connectedCustomer = connectedCustomer;
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

        JLabel lblJoon = new JLabel(connectedCustomer.getFirstName());
        lblJoon.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblJoon.setHorizontalAlignment(SwingConstants.CENTER);
        lblJoon.setBounds(164, 45, 69, 23);
        frame.getContentPane().add(lblJoon);

        JButton btnRecord = new JButton("Record");
        btnRecord.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnRecord.setBounds(152, 101, 89, 23);
        frame.getContentPane().add(btnRecord);
        btnRecord.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                System.out.println("Connected client's ip: " + connectedCustomer.getIpAddress().toString());
                // Call method to record and send
                voiceController = new VoiceController();
                voiceController.setDestinationIP(connectedCustomer.getIpAddress());

                System.out.println("Going to capture audio");
                voiceController.captureAudio();
                btnRecord.setEnabled(false);

            }
        });

        JButton btnStop = new JButton("Stop");
        btnStop.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnStop.setBounds(152, 135, 89, 23);
        frame.getContentPane().add(btnStop);
        btnStop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                voiceController.stopCapturingVoice();
                btnStop.setEnabled(false);

            }
        });
    }




}
