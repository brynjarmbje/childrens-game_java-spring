package com.game.repository;

import com.game.entity.Admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    // JPA should implement the methods

    Admin findByUsername(String username);

    Admin save(Admin admin);

    void delete(Admin admin);

}
