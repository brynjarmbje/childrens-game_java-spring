package com.login.persistance.repositories;

import com.login.persistance.repositories.User; 

import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username); //maybe use optional so you can return no result 
}
