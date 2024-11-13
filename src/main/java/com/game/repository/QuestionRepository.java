package com.game.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.game.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    // Additional custom methods for Question, if needed
}