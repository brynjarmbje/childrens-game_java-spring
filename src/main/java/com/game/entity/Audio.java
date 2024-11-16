package com.game.entity;

import java.sql.Blob;

import jakarta.persistence.*;

@Entity
@Table(name = "audio")
public class Audio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Lob
    @Column(nullable = false)
    private Blob audioData;

    public Audio() {
    }

    public Audio(String name,  Blob audioData) {
        this.name = name;
        this.audioData = audioData;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public Blob getAudioData() {
        return audioData;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setAudioData(Blob audioData) {
        this.audioData = audioData;
    }
}
