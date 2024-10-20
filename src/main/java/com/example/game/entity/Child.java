package com.example.game.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column()
    private String name;

    @ElementCollection
    private List<Integer> level;

    @ElementCollection
    private List<List<Integer>> progress;

    // References to the last sessions
/*    @OneToOne
    private GameSession lastLetterSession;

    @OneToOne
    private GameSession lastNumberSession;

    @OneToOne
    private GameSession lastVisualSession;

    @OneToOne
    private MemorySession lastMemorySession;*/

    // Default constructor
    public Child() {}

    // Parameterized constructor
    public Child(String name, List<Integer> level, List<List<Integer>> progress) {
        this.name = name;
        this.level = level;
        this.progress = progress;
    }

    // Getters and setters
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

    public List<Integer> getLevel() {
        return level;
    }

    public void setLevel(List<Integer> level) {
        this.level = level;
    }

    public List<List<Integer>> getProgress() {
        return progress;
    }

    public void setProgress(List<List<Integer>> progress) {
        this.progress = progress;
    }
}
