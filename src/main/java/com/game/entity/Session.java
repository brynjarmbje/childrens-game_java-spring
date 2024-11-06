package com.game.entity;public class Session {
    private Long id;
    private int type;
    private int level;

	public Session(Long id, int type, int level) {
		this.id = id;
		this.type = type;
        this.level = level;
	}
    
}
