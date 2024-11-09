package com.game.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Image {

    @Id
    private String id;

    private String name;
    private int type;

    // Constructor
    public Image(String id, String name, int type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    // Default Constructor
    public Image() {}

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
