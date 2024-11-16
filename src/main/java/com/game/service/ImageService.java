package com.game.service;

import com.game.entity.Image;
import com.game.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private DataSource dataSource;

    public Image getImageById(Long id) throws SQLException {
        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(false);
        return imageRepository.findById(id).orElseThrow(() -> new RuntimeException("Image not found with id: " + id));
    }
}
