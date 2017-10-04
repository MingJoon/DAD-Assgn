package com.dad.controller;


import com.dad.voice.CaptureVoiceThread;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class VoiceController {

    private AudioFormat audioFormat;
    private TargetDataLine targetDataLine;
    private InetAddress destinationIP;
    private CaptureVoiceThread captureThread;


    public static AudioFormat getAudioFormat() {
        float sampleRate = 16000.0F;
        int sampleInbits = 16;
        int channels = 1;
        boolean signed = true;
        boolean bigEndian = false;
        return new AudioFormat(sampleRate, sampleInbits, channels, signed, bigEndian);
    }

    public void stopCapturingVoice(){
        captureThread.stopAudioCapture();
    }

    public void captureAudio() {
        try {

            audioFormat = getAudioFormat();
            DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class, audioFormat);
            targetDataLine = (TargetDataLine) AudioSystem.getLine(dataLineInfo);
            targetDataLine.open(audioFormat);
            targetDataLine.start();

            System.out.println("Creating a new capture thread");
            captureThread = new CaptureVoiceThread(targetDataLine, destinationIP);
            captureThread.start();

        } catch (Exception e) {
            StackTraceElement stackEle[] = e.getStackTrace();
            for (StackTraceElement val : stackEle) {
                System.out.println(val);
            }
            System.exit(0);
        }
    }


    public void setDestinationIP(InetAddress destinationIP) {
        this.destinationIP = destinationIP;
    }
}
