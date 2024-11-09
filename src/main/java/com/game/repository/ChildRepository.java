package com.game.repository;

import com.game.entity.Child;
import com.game.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ChildRepository extends JpaRepository<Child, Long> {
    // JPA should implement the methods

    List<Child> findAll();

    List<Child> findByName(String name);

    List<Child> findBySchool(School school);
}
