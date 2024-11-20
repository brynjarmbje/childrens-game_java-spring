package com.game.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Session {

    @Id
    private String id;

    private int level;
    private int progress; // Games won in the current level

    @OneToOne
    private Child child;

    public Session(String id, int level, int progress, Child child) {
        this.id = id;
        this.level = level;
        this.progress = progress;
        this.child = child;
    }

    public Session() {}

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
    }
}
