package com.game.controller;

import com.game.entity.Image;
import com.game.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.SQLException;

@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private DataSource dataSource;

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            // Fetch Image entity from DB
            Image image = imageService.getImageById(id);
            // Get byte[] data directly
            byte[] imageBytes = image.getImageData();

            if (imageBytes == null || imageBytes.length == 0) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT); // No image data found
            }

            // Create response headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG); // Change this based on your image format (e.g., IMAGE_JPEG)

            // Return image bytes in response
            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);

        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}
