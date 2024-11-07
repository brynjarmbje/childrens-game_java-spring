package com.game.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Memory {

    @Id
    private String id;
    private String name;
    private String lastSession;

    // Constructor
    public Memory(String id, String name, String lastSession) {
        this.id = id;
        this.name = name;
        this.lastSession = lastSession;
    }

    // Default Constructor
    public Memory() {}

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastSession() {
        return lastSession;
    }

    public void setLastSession(String lastSession) {
        this.lastSession = lastSession;
    }
}
