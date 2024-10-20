package com.example.game.repository;

import com.example.game.entity.Admin;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
	
	// JPA should implement the method
	// optional so as to deal better with null, test
	Optional<Admin> findByUsername(String username);
}
