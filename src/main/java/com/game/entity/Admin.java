package com.game.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(name = "issupervisor")
    private boolean isSupervisor;

    @ManyToOne
    @JoinColumn(name = "school")
    private School school;

    // Default constructor
    public Admin() {
    }

    // Parameterized constructor
    public Admin(String username, String password, boolean isSupervisor, School school) {
        this.username = username;
        this.password = password;
        this.isSupervisor = isSupervisor;
        this.school = school;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isSupervisor() {
        return isSupervisor;
    }

    public void setSupervisor(boolean isSupervisor) {
        this.isSupervisor = isSupervisor;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public void createAdmin(String username, String password, boolean isSupervisor, School school) {
        this.username = username;
        this.password = password;
        this.isSupervisor = isSupervisor;
    }
}
