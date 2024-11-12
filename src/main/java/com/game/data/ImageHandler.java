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

    // Method to copy all image files in a directory to .blob files
    public static void convertAllImageFilesToBlob(String directoryPath, String format) {
        File directory = new File(directoryPath);
        if (!directory.isDirectory()) {
            System.out.println("The specified path is not a directory.");
            return;
        }

        File[] files = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".png") || name.toLowerCase().endsWith(".jpg"));
        if (files != null) {
            for (File imageFile : files) {
                try {
                    // Load the image
                    BufferedImage img = ImageIO.read(imageFile);

                    // Convert the image to a byte array
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    ImageIO.write(img, format, outputStream);
                    byte[] imageBlob = outputStream.toByteArray();

                    // Create a .blob file path by replacing the image extension
                    String blobFilePath = imageFile.getAbsolutePath().replaceFirst("\\.(png|jpg)$", ".blob");
                    try (FileOutputStream fos = new FileOutputStream(blobFilePath)) {
                        fos.write(imageBlob);
                        System.out.println("Converted " + imageFile.getName() + " to blob at: " + blobFilePath);
                    }
                } catch (IOException e) {
                    System.err.println("Failed to convert " + imageFile.getName() + " to blob.");
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("No image files found in directory: " + directoryPath);
        }
    }

    public static void main(String[] args) {
        String filePath = "src/main/resources/static/image_files/test_image/image_test.png";
        String blobFilePath = "src/main/resources/static/image_files/test_image/image_test.blob";
        String newFilePath = "src/main/resources/static/image_files/test_image/image_test_copy.png";

        System.out.println("Trying to load image file from: " + new File(filePath).getAbsolutePath());

        ImageHandler handler = new ImageHandler(filePath);
        handler.display();

        // Convert the image to a blob (byte array)
        byte[] imageBlob = handler.convertImageToBlob("png");
        if (imageBlob != null) {
            System.out.println("Image file converted to blob (byte array) of size: " + imageBlob.length + " bytes.");

            // Save the blob as a .blob file
            try (FileOutputStream fos = new FileOutputStream(blobFilePath)) {
                fos.write(imageBlob);
                System.out.println("Blob saved to file at: " + blobFilePath);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Write the blob back to a new image file
            handler.writeBlobToImageFile(imageBlob, newFilePath, "png");
            System.out.println("New image file created at: " + newFilePath);
        } else {
            System.out.println("Failed to convert image to blob.");
        }
        String hlutirDirectoryPath = "src/main/resources/static/image_files/hlutir";
        System.out.println("Converting all .png files in directory: " + new File(hlutirDirectoryPath).getAbsolutePath());
        convertAllImageFilesToBlob(hlutirDirectoryPath, "png");

        String nedanOfanDirectoryPath = "src/main/resources/static/image_files/nedan_ofan_haegri_vinstri";
        System.out.println("Converting all .png files in directory: " + new File(nedanOfanDirectoryPath).getAbsolutePath());
        convertAllImageFilesToBlob(nedanOfanDirectoryPath, "png");

        String numerDirectoryPath = "src/main/resources/static/image_files/numer";
        System.out.println("Converting all .png files in directory: " + new File(numerDirectoryPath).getAbsolutePath());
        convertAllImageFilesToBlob(numerDirectoryPath, "png");

        String stafrofDirectoryPath = "src/main/resources/static/image_files/stafrof";
        System.out.println("Converting all .png files in directory: " + new File(stafrofDirectoryPath).getAbsolutePath());
        convertAllImageFilesToBlob(stafrofDirectoryPath, "png");
    }
}
