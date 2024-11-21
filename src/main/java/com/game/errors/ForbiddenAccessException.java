package com.game.errors;

// Custom exception for Forbidden (403)
public class ForbiddenAccessException extends RuntimeException {
    public ForbiddenAccessException(String message) {
        super(message);
    }
}
