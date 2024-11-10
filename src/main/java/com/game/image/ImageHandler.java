package com.game.image;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
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

    // Convert the image to a byte array (blob-like behavior)
    public byte[] convertImageToBlob(String format) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, format, outputStream);
            return outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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

    public static void main(String[] args) {
        String filePath = "src/main/resources/static/image_files/test_image/image_test.png";
        String newFilePath = "src/main/resources/static/image_files/test_image/image_test_copy.png";

        System.out.println("Trying to load image file from: " + new File(filePath).getAbsolutePath());

        ImageHandler handler = new ImageHandler(filePath);
        handler.display();

        // Convert the image to a blob (byte array)
        byte[] imageBlob = handler.convertImageToBlob("png");
        if (imageBlob != null) {
            System.out.println("Image file converted to blob (byte array) of size: " + imageBlob.length + " bytes.");

            // Write the blob back to a new image file
            handler.writeBlobToImageFile(imageBlob, newFilePath, "png");
            System.out.println("New image file created at: " + newFilePath);
        } else {
            System.out.println("Failed to convert image to blob.");
        }
    }
}
