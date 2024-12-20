package com.game.repository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.game.entity.Question;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    // Find a question by its ID
//    Optional<Question> findById(Long id);

    // Find a question by its name
    Optional<Question> findByName(String name);

    Optional<Question> findQuestionByType(int type);

    // Find all questions by type
    List<Question> findByType(int type);

    // Find all questions by level
    List<Question> findByLevel(int level);

    @Query(value = "SELECT q FROM Question q WHERE q.type = :type ORDER BY random()")
    List<Question> findRandomQuestionsByType(@Param("type") int type, Pageable pageable);


    List<Question> findByIdBetween(long minId, long maxId);
}