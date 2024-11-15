package com.game.data;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sql.rowset.serial.SerialBlob;

import com.game.repository.GameRepository;

import java.io.*;
import java.nio.file.Files;
import java.sql.Blob;
import java.sql.SQLException;

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
    public static Blob convertAudioFileToBlob(String filePath) {
        File audioFile = new File(filePath);
        byte[] audioBytes = null;
        try (InputStream inputStream = new FileInputStream(audioFile)) {
            audioBytes = new byte[(int) audioFile.length()];
            inputStream.read(audioBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Convert byte array to Blob
        Blob blob = null;
        try {
            blob = new SerialBlob(audioBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return blob;
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

    // Method to copy all .wav files in a directory to .blob files
    public static void convertAllWavFilesToBlob(String directoryPath) {
        File directory = new File(directoryPath);
        if (!directory.isDirectory()) {
            System.out.println("The specified path is not a directory.");
            return;
        }

        File[] files = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".wav"));
        if (files != null) {
            for (File wavFile : files) {
                try {
                    // Read the .wav file into a byte array
                    byte[] audioBlob = Files.readAllBytes(wavFile.toPath());

                    // Create a .blob file path by replacing the .wav extension
                    String blobFilePath = wavFile.getAbsolutePath().replace(".wav", ".blob");
                    try (FileOutputStream fos = new FileOutputStream(blobFilePath)) {
                        fos.write(audioBlob);
                        System.out.println("Converted " + wavFile.getName() + " to blob at: " + blobFilePath);
                    }
                } catch (IOException e) {
                    System.err.println("Failed to convert " + wavFile.getName() + " to blob.");
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("No .wav files found in directory: " + directoryPath);
        }
    }

    // Method to play audio from a Blob
    public void playAudioBlob(Blob audioBlob) {
        try {
            // Extract byte array from Blob
            byte[] audioBytes = audioBlob.getBytes(1, (int) audioBlob.length());
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(audioBytes);

            // Create an AudioInputStream from the byte array
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(byteArrayInputStream);

            // Get a Clip instance and open the AudioInputStream
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            // Start playing the audio
            clip.start();
            System.out.println("Audio from Blob is playing...");
        } catch (SQLException | IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
            System.err.println("Failed to play audio from Blob.");
        }
    }

    public static void main(String[] args) {
        /*String filePath = "src/main/resources/static/audio_files/test_audio/audio_test.wav";
        String blobFilePath = "src/main/resources/static/audio_files/test_audio/audio_test.blob";
        String newFilePath = "src/main/resources/static/audio_files/test_audio/audio_test_copy.wav";

        System.out.println("Trying to load audio file from: " + new File(filePath).getAbsolutePath());

        AudioHandler player = new AudioHandler(filePath);
        player.play();  // Play once

        // Convert the audio file to a blob (byte array)
        byte[] audioBlob = player.convertAudioFileToBlob(filePath);
        System.out.println("Audio file converted to blob (byte array) of size: " + audioBlob.length + " bytes.");

        // Save the blob as a .blob file
        try (FileOutputStream fos = new FileOutputStream(blobFilePath)) {
            fos.write(audioBlob);
            System.out.println("Blob saved to file at: " + blobFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Write the blob back to a new audio file
        player.writeBlobToAudioFile(audioBlob, newFilePath);
        System.out.println("New audio file created at: " + newFilePath);

        try {
            Thread.sleep(10000);  // Let the audio play for 10 seconds
            player.stop();  // Stop playing
            player.close();  // Release resources
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String hlutirDirectoryPath = "src/main/resources/static/audio_files/hlutir";
        System.out.println("Converting all .wav files in directory: " + new File(hlutirDirectoryPath).getAbsolutePath());
        convertAllWavFilesToBlob(hlutirDirectoryPath);
		GameRepository.saveAudio(Blob hlutirDirectoryPath)

        String nedanOfanDirectoryPath = "src/main/resources/static/audio_files/nedan_ofan_haegri_vinstri";
        System.out.println("Converting all .wav files in directory: " + new File(nedanOfanDirectoryPath).getAbsolutePath());
        convertAllWavFilesToBlob(nedanOfanDirectoryPath);

        String numerDirectoryPath = "src/main/resources/static/audio_files/numer";
        System.out.println("Converting all .wav files in directory: " + new File(numerDirectoryPath).getAbsolutePath());
        convertAllWavFilesToBlob(numerDirectoryPath);

        String stafrofDirectoryPath = "src/main/resources/static/audio_files/stafrof";
        System.out.println("Converting all .wav files in directory: " + new File(stafrofDirectoryPath).getAbsolutePath());
        convertAllWavFilesToBlob(stafrofDirectoryPath);*/
    }
}
