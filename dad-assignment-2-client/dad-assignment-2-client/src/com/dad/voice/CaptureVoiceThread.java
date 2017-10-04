package com.dad.voice;


import javax.sound.sampled.TargetDataLine;
import java.io.ByteArrayOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class CaptureVoiceThread extends Thread {

    private ByteArrayOutputStream byteArrayOutputStream;
    private boolean stopAudioCapture;
    private TargetDataLine targetDataLine;
    private InetAddress destinationIp;

    // Set buffer size
    private byte tempBuffer[] = new byte[10000];

    public CaptureVoiceThread(TargetDataLine targetDataLine, InetAddress destinationIp){
        System.out.println("Inside capture thread constructor");
        this.targetDataLine = targetDataLine;
        this.stopAudioCapture = false;
        this.byteArrayOutputStream = new ByteArrayOutputStream();
        this.destinationIp = destinationIp;
    }

    public void stopAudioCapture(){
        System.out.println("Stopping audio capture");
        stopAudioCapture = true;
    }

    public void run() {
        try {

            System.out.println("inside capture thread run method");
            // Create datagram socket
            DatagramSocket clientSocket = new DatagramSocket(40000);
            System.out.println();


            // Keep recording
            while (!stopAudioCapture) {
                int cnt = targetDataLine.read(tempBuffer, 0, tempBuffer.length);

                if (cnt > 0) {
                    // Send recorded packet to server
                    DatagramPacket sendPacket = new DatagramPacket(tempBuffer, tempBuffer.length, destinationIp, 41000);
                    clientSocket.send(sendPacket);
                    byteArrayOutputStream.write(tempBuffer, 0, cnt);
                }
            }

            // Close after recording
            byteArrayOutputStream.close();

        } catch (Exception e) {
            System.out.println("CaptureThread::run()" + e);
            System.exit(0);
        }
    }
}
