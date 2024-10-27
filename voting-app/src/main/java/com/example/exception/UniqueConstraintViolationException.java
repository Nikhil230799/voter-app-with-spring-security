package com.example.exception;

public class UniqueConstraintViolationException extends RuntimeException {
    private final String fieldName;

    public UniqueConstraintViolationException(String fieldName, String message) {
        super(message);
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
