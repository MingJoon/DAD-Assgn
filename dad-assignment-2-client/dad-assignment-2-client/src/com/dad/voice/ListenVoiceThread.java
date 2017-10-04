package com.dad.voice;


import com.dad.controller.VoiceController;

import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ListenVoiceThread extends Thread {


    private SourceDataLine sourceLine;
    private AudioInputStream audioInputStream;


    public ListenVoiceThread(){

    }

    public void run(){
        try {
            byte[] receiveData = new byte[10000];
            DatagramSocket serverSocket = new DatagramSocket(41000);
//            System.out.println("Inside start receiving method " + serverSocket.getLocalAddress().getHostAddress());

            while(true){
                System.out.println("Looping to receive datagram sent");
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                System.out.println("RECEIVED: " + receivePacket.getAddress().getHostAddress() + " " + receivePacket.getPort());

                try{
                    /**
                     * Convert received data from Datagram packet to AudioInputStream
                     */
                    byte audioData[] = receivePacket.getData();
                    InputStream byteInputStream = new ByteArrayInputStream(audioData);
                    AudioFormat adFormat = VoiceController.getAudioFormat();
                    audioInputStream = new AudioInputStream(byteInputStream, adFormat, audioData.length / adFormat.getFrameSize());


                    DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, adFormat);
                    sourceLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
                    sourceLine.open(adFormat);
                    sourceLine.start();


                    Thread playThread = new Thread(new PlayVoiceThread(audioInputStream,sourceLine));
                    playThread.start();

                } catch (LineUnavailableException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}