package com.game.entity;

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

	@Column()
    private boolean isSupervisor;

	@ManyToOne
	@JoinColumn(name = "school")
	private School school;

	// Default constructor
    public Admin() {}

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

    public void setAsSupervisor() {
        this.isSupervisor = true;
    }

    public void removeAsSupervisor() {
        this.isSupervisor = false;
    }

    public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}
}
