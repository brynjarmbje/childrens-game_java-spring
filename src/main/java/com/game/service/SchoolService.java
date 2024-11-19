package com.game.service;

import com.game.entity.Admin;
import com.game.entity.Child;
import com.game.entity.School;
import com.game.repository.AdminRepository;
import com.game.repository.ChildRepository;
import com.game.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolService {

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ChildRepository childRepository;

    public void createSchool(String schoolName) {
        School school = new School(schoolName);
        schoolRepository.save(school);
    }

    public void createSupervisor(String username, String password, Long schoolId) {
        // Fetch the School entity by its ID
        School school = schoolRepository.findById(schoolId).orElseThrow(() ->
                new IllegalArgumentException("School not found with id: " + schoolId));

        // Create and populate the Admin entity
        Admin supervisor = new Admin();
        supervisor.setUsername(username);
        supervisor.setPassword(password);
        supervisor.setSupervisor(true);
        supervisor.setSchool(school); // Set the School entity

        // Save the Admin entity
        adminRepository.save(supervisor);
    }
    public void createSchoolAdmin(String username, String password, Long schoolId) {
        // Fetch the School entity by its ID
        School school = schoolRepository.findById(schoolId).orElseThrow(() ->
                new IllegalArgumentException("School not found with id: " + schoolId));

        // Create and populate the Admin entity
        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(password);
        admin.setSupervisor(false);
        admin.setSchool(school); // Set the School entity

        // Save the Admin entity
        adminRepository.save(admin);
    }

    public void createSchoolChild(String name, Long schoolId) {
        // Fetch the School entity by its ID
        School school = schoolRepository.findById(schoolId).orElseThrow(() ->
                new IllegalArgumentException("School not found with id: " + schoolId));

        // Create and populate the Child entity
        Child child = new Child();
        child.setName(name);
        child.setSchool(school); // Set the School entity

        // Save the Child entity
        childRepository.save(child);
    }
}
