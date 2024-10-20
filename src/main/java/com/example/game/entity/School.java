package com.example.game.entity;


import jakarta.persistence.*;
import java.util.List;


@Entity
public class School {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


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
    public School() {}


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

