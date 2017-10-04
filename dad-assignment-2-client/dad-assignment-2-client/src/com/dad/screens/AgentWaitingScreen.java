package com.dad.screens;

import com.dad.controller.AgentController;
import com.dad.controller.VoiceController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by ifand on 20/6/2017.
 */
public class AgentWaitingScreen {

    private JFrame frame;
    private VoiceController voiceController;

    public static void NavigateToAgentWaitingScreen() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AgentWaitingScreen window = new AgentWaitingScreen();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public AgentWaitingScreen() {
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

        JLabel lblWaiting = new JLabel("Waiting for Customer...");
        lblWaiting.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblWaiting.setHorizontalAlignment(SwingConstants.CENTER);
        lblWaiting.setBounds(100, 45, 200, 23);
        frame.getContentPane().add(lblWaiting);
    }

}
