package com.game.data;

import javax.imageio.ImageIO;
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
}