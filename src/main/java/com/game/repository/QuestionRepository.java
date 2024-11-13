package com.game.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.game.entity.Question;

import java.util.Optional;
import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

}