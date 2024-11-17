package com.game.repository;

import com.game.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import com.game.entity.Image;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {
}