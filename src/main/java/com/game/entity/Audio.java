//package com.game.entity;
//
//import java.sql.Blob;
//
//import jakarta.persistence.*;
//
//@Entity
//@Table(name = "audio")
//public class Audio {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//
//    @Column(nullable = false)
//    private String name;
//
//    @Column(nullable = false)
//    private int type;
//
//    @Column(name = "audio_data", columnDefinition = "BYTEA")
//    private byte[] audioData;
//
//    public Audio() {
//    }
//    public Audio(String name, int type, byte[] audioData) {
//        this.name = name;
//        this.type = type;
//        this.audioData = audioData;
//    }
//
//    public long getId() {
//        return id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public int getType() {
//        return type;
//    }
//
//    public byte[] getAudioData() {
//        return audioData;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setType(int type) {
//        this.type = type;
//    }
//
//    public void setAudioData(byte[] audioData) {
//        this.audioData = audioData;
//    }
//}
