package com.game.repository;

import com.game.entity.Admin;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    // JPA should implement the method
    // optional so as to deal better with null, test
    static Optional<Admin> findByUsername(String username) {
        return null;
    }
}
