package org.woped.file.t2p;

import javax.sound.sampled.*;
import java.io.*;

/**
 * Sound Aufnahme in Java
 */
public class JavaSoundRecorder {
    // Aufnahmezeit
    static final long RECORD_TIME = 10000;  // 1 minute

    // Pfad der wav datei
    private File wavFile = new File("test.wav");
    // format des audio files
    private AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;
    
    private TargetDataLine line;

    /**
     * Deklariere audio format
     */
    public AudioFormat getAudioFormat() {
        float sampleRate = 8000;
        int sampleSizeInBits = 16;
        int channels = 2;
        boolean signed = true;
        boolean bigEndian = false;
        AudioFormat format = new AudioFormat(sampleRate, sampleSizeInBits,
                channels, signed, bigEndian);
        return format;
    }

    /**
     * Nimmt Sprache auf und speichert diese in waw Datei
     */
    public void start() {
        try {
            AudioFormat format = getAudioFormat();
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

            // checks if system supports the data line
            if (!AudioSystem.isLineSupported(info)) {
                System.out.println("Line not supported");
                System.exit(0);
            }
            line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();   // start capturing

            System.out.println("Start capturing...");

            AudioInputStream ais = new AudioInputStream(line);

            System.out.println("Start recording...");

            // start recording
            AudioSystem.write(ais, fileType, wavFile);

        } catch (LineUnavailableException ex) {
            ex.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Beendet Sprachaufnahme
     */
    public void finish() {
        line.stop();
        line.close();
        System.out.println("Finished");
    }

}