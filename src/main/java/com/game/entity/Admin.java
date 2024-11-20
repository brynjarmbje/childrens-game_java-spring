package com.game.entity;

import jakarta.persistence.*;

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

    @Column(name = "is_supervisor")
    private boolean isSupervisor = false; // Default to false

    @ManyToOne
    @JoinColumn(name = "school")
    private School school;



    @ManyToMany
    @JoinTable(
            name = "admin_child",
            joinColumns = @JoinColumn(name = "admin_id"),
            inverseJoinColumns = @JoinColumn(name = "child_id")
    )
    private List<Child> children;


    // Parameterized constructor
    public Admin(String username, String password, boolean isSupervisor, School school) {
        this.username = username;
        this.password = password;
        this.isSupervisor = isSupervisor;
        this.school = school;
    }

    public Admin() {

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

    public void addChildToAdmin(Child child) {
        if (!children.contains(child)) {
            children.add(child);
        }
    }

    public void removeChildFromAdmin(Child child) {
        if (children.contains(child)) {
            children.remove(child);
        }
    }

    public void clearChildrenFromAdmin() {
        children.clear();
    }
    public void removeChildFromAdmin(Long childId) {
        for (Child child : children) {
            if (child.getId().equals(childId)) {
                children.remove(child);
                break;
            }
        }
    }

}
