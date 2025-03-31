package com.example.exception;

public class CandidatesNotFoundException extends RuntimeException {

    public CandidatesNotFoundException(String message) {
        super(message);

    }

}