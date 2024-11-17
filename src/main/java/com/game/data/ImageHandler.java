package com.game.data;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class ImageHandler {
    private BufferedImage image;

    // Constructor to initialize the ImageHandler
    public ImageHandler(String filePath) {
        try {
            // Load an image from the file path
            File imageFile = new File(filePath);
            image = ImageIO.read(imageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Constructor to initialize the ImageHandler
    public ImageHandler() {
    }

    // Display the image in a JFrame window
    public void display() {
        if (image != null) {
            JFrame frame = new JFrame("Image Display");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(image.getWidth(), image.getHeight());
            JLabel label = new JLabel(new ImageIcon(image));
            frame.add(label);
            frame.pack();
            frame.setVisible(true);
        } else {
            System.out.println("No image loaded to display.");
        }
    }

    // Convert the image to a blob
    public static byte[] convertImageToBlob(BufferedImage image, String format) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            // Write the image data to the output stream in the specified format
            ImageIO.write(image, format, outputStream);
            // Return the byte array representation of the image
            return outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to convert image to byte array.");
        }
        return null; // Return null in case of an error
    }

    // Write a byte array (blob) back to an image file
    public void writeBlobToImageFile(byte[] imageBlob, String outputFilePath, String format) {
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(imageBlob)) {
            BufferedImage newImage = ImageIO.read(inputStream);
            ImageIO.write(newImage, format, new File(outputFilePath));
            System.out.println("Image file written to: " + new File(outputFilePath).getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}