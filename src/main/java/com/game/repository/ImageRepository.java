package com.game.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.game.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
    // Additional query methods if needed

}
