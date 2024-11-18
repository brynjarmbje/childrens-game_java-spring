package com.game.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "is_supervisor", nullable = false)
    private boolean isSupervisor = false; // Default to false


    @ManyToMany
    @JoinTable(
            name = "admin_child",
            joinColumns = @JoinColumn(name = "admin_id"),
            inverseJoinColumns = @JoinColumn(name = "child_id")
    )

    @LazyCollection(LazyCollectionOption.FALSE) // Ensures children are fetched with admin
    private List<Child> children = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "school")
    private School school;

    // Default constructor
    public Admin() {
    }

    // Parameterized constructor
    public Admin(String username, String password, boolean isSupervisor, School school, List<Child> children) {
        this.username = username;
        this.password = password;
        this.isSupervisor = isSupervisor;
        this.school = school;
        this.children = children;
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

    public List<Child> getChildren() {
        return children;
    }
    public void setChildren(List<Child> children) {
        this.children = children;
    }
    public void addChild(Child child) {
        children.add(child);
        child.getAdmins().add(this);
    }
    public void removeChild(Child child) {
        children.remove(child);
        child.getAdmins().remove(this);
    }
}
