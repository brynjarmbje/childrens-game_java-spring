package com.game.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "school")
public class School {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true, nullable = false)
	private String name;

	 @OneToMany(mappedBy = "school", cascade = CascadeType.ALL)
	private List<Admin> admins;

	 @OneToMany(mappedBy = "school", cascade = CascadeType.ALL)
	private List<Child> children;

	// Constructor
	public School(String name) {
		this.name = name;
	}

	// Default constructor
	public School() {
	}

	// Getters and Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Admin> getAdmins() {
		return admins;
	}

	public void setAdmins(List<Admin> admins) {
		this.admins = admins;
	}

	public List<Child> getChildren() {
		return children;
	}

	public void setChildren(List<Child> children) {
		this.children = children;
	}
}
