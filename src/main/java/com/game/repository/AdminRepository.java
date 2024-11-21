package com.game.repository;

import com.game.entity.Admin;

import com.game.entity.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    // JPA should implement the methods

    Admin findByUsername(String username);

    Admin save(Admin admin);

    void delete(Admin admin);

    List<Admin> findAll();

    List<Admin> findByIsSupervisorFalse();

    @Query("SELECT a FROM Admin a " +
            "WHERE a.school.id = (SELECT ad.school.id FROM Admin ad WHERE ad.id = :adminId) " +
            "AND a.isSupervisor = false " +
            "AND a.id != :adminId")
    List<Admin> findAdminsInSameSchoolNotSupervisor(@Param("adminId") Long adminId);
}
