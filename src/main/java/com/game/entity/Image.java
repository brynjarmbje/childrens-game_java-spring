package com.game.entity;

import jakarta.persistence.*;

@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String connectionId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int type;

    @Column(nullable = true)
    private Character firstLetter;

    @Column(nullable = true)
    private boolean isLetter;

    @Lob
    @Column(nullable = false)
    private byte[] imageData;

    // Constructor
    public Image(long id, String name, int type, byte[] imageData) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.imageData = imageData;
    }


    // Constructor with firstLetter
    public Image(long id, String name, int type, Character firstLetter, byte[] imageData) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.firstLetter = firstLetter;
        this.imageData = imageData;
    }

    // Constructor with isLetter
    public Image(long id, String name, int type, boolean isLetter, byte[] imageData) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.isLetter = isLetter;
        this.imageData = imageData;
    }

    // Default Constructor
    public Image() {}

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public Character getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(Character firstLetter) {
        this.firstLetter = firstLetter;
    }

    public boolean getIsLetter() {
        return isLetter;
    }

    public boolean isLetter() {
        return isLetter;
    }
}
