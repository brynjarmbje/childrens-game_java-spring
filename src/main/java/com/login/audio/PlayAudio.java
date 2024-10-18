package com.login.audio;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class PlayAudio {
    private Clip clip;

    // Constructor to initialize the AudioPlayer
    public PlayAudio(String filePath) {
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

    public static void main(String[] args) {
        String filePath = "src/main/audiofiles/test_sound.wav";
        System.out.println("Trying to load audio file from: " + new File(filePath).getAbsolutePath());

        PlayAudio player = new PlayAudio(filePath);
        player.play();  // Play once

        try {
            Thread.sleep(10000);  // Let the audio play for 10 seconds
            player.stop();  // Stop playing
            player.close();  // Release resources
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}