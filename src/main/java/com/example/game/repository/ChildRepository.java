package com.example.game.repository;

import com.example.game.entity.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildRepository extends JpaRepository<Child, Long> {
    // You can add custom query methods here if needed
}
