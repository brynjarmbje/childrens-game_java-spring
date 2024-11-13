package com.game.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.game.entity.Question;

import java.util.Optional;
import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    // Find a question by its ID
    Optional<Question> findById(Long id);

    // Find a question by its name
    Optional<Question> findByName(String name);

    // Find all questions by type
    List<Question> findByType(int type);

    // Find all questions by level
    List<Question> findByLevel(int level);
}