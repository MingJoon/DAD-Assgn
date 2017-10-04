package com.dad.voice;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.SourceDataLine;

public class PlayVoiceThread extends Thread {

    private AudioInputStream inputStream;
    private SourceDataLine sourceDataLine;

    public PlayVoiceThread(AudioInputStream inputStream, SourceDataLine sourceDataLine){
        this.inputStream = inputStream;
        this.sourceDataLine = sourceDataLine;
    }

    byte temporaryBuffer[] = new byte[10000];

    public void run() {
        try {
            int count;
            while ((count = inputStream.read(temporaryBuffer, 0, temporaryBuffer.length)) != -1) {
                if (count > 0) {
                    sourceDataLine.write(temporaryBuffer, 0, count);
                }
            }
    //                sourceDataLine.drain();
        //            sourceDataLine.close();
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }
    }

}
