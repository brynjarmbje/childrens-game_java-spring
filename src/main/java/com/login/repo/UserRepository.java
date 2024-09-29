package com.login.repo;

import com.login.repo.User; 

import java.util.Optional;

import org.springframework.stereotype.Repository;
//import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository {//NOTE:maybe use for ease of setup extends JpaRepository<User, Long> 
    Optional<User> findByUsername(String username); //maybe use optional so you can return no result 
}
