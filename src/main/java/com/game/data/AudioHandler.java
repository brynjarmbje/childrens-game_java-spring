package com.game.data;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sql.rowset.serial.SerialBlob;

import com.game.repository.GameRepository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.sql.Blob;

public class AudioHandler {
    private Clip clip;

    // Constructor to initialize the AudioPlayer
    public AudioHandler(String filePath) {
        try {
            // Open an audio input stream from the file path
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            // Get a sound clip resource
            clip = AudioSystem.getClip();

            // Open audio clip and load samples from the audio input stream
            clip.open(audioStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // Play or resume the audio clip
    public void play() {
        if (clip != null && !clip.isRunning()) {
            clip.start();
        }
    }

    // Stop the clip
    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.setFramePosition(0);  // rewind to the beginning
        }
    }

    // Close the clip and release resources
    public void close() {
        if (clip != null) {
            clip.close();
        }
    }

    // Convert the audio file to a byte array (blob-like behavior)
    public static byte[] convertAudioFileToBlob(String filePath) {
        File audioFile = new File(filePath);
        byte[] audioBytes = null;
        try (InputStream inputStream = new FileInputStream(audioFile)) {
            audioBytes = new byte[(int) audioFile.length()];
            inputStream.read(audioBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return audioBytes;
    }

    // Write a byte array (blob) back to an audio file
    public void writeBlobToAudioFile(byte[] audioBlob, String outputFilePath) {
        try (OutputStream outputStream = new FileOutputStream(outputFilePath)) {
            outputStream.write(audioBlob);
            System.out.println("Audio file written to: " + new File(outputFilePath).getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
