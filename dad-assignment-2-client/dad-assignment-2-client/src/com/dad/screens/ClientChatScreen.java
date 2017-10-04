package com.dad.screens;

import com.dad.controller.AgentController;
import com.dad.controller.CustomerController;
import com.dad.controller.VoiceController;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class ClientChatScreen {

    private JFrame frame;
    private VoiceController voiceController;

    /**
     * Launch the application.
     */
    public static void NavigateToClientChatScreen() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ClientChatScreen window = new ClientChatScreen();
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
    public ClientChatScreen() {
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


        JLabel lblPritish = new JLabel("Agent " + " " + CustomerController.getCustomer().getAgentAssociate().getFirstName());
        lblPritish.setHorizontalAlignment(SwingConstants.CENTER);
        lblPritish.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblPritish.setBounds(167, 28, 63, 24);
        frame.getContentPane().add(lblPritish);

        JButton btnRecord = new JButton("Record");
        btnRecord.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnRecord.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // Call method to record and send
                voiceController = new VoiceController();
                voiceController.setDestinationIP(CustomerController.getCustomer().getAgentAssociate().getIpAddress());

                System.out.println("Going to capture audio");
                voiceController.captureAudio();
                btnRecord.setEnabled(false);


            }
        });
        btnRecord.setBounds(152, 89, 89, 23);
        frame.getContentPane().add(btnRecord);

        JButton btnStop = new JButton("Stop");
        btnStop.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnStop.setBounds(152, 136, 89, 23);

        btnStop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                voiceController.stopCapturingVoice();
                btnStop.setEnabled(false);
            }

        });

        frame.getContentPane().add(btnStop);
    }

}
