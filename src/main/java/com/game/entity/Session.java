package com.game.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Session {

    @Id
    private String id;

    private String name;

    @OneToOne
    private Session lastSession;

    // Constructor
    public Session(String id, String name, Session lastSession) {
        this.id = id;
        this.name = name;
        this.lastSession = lastSession;
    }

    // Default Constructor
    public Session() {}

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

    public Session getLastSession() {
        return lastSession;
    }

    public void setLastSession(Session lastSession) {
        this.lastSession = lastSession;
    }
}
