package com.dad.controller;


import com.dad.voice.ListenVoiceThread;
import com.dad.voice.PlayVoiceThread;

import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class VoiceReceiverController{

    private SourceDataLine sourceLine;
    private AudioInputStream audioInputStream;

    public VoiceReceiverController(){


    }

    public void startReceving(){
        Thread listenThread = new Thread(new ListenVoiceThread());
        listenThread.start();

    }


}
