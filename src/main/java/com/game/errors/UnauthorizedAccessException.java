package com.game.errors;

// Custom exception for Unauthorized (401)
public class UnauthorizedAccessException extends RuntimeException {
    public UnauthorizedAccessException(String message) {
        super(message);
    }
}