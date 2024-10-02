package com.example.exception;

public class EmptyAuthHeaderException extends RuntimeException {
    public EmptyAuthHeaderException(String message) {
        super(message);
    }
}