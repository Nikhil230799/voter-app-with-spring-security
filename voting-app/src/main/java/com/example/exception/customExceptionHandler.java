package com.example.exception;


import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;

import com.example.common.Response;

@RestController
@ControllerAdvice
public class customExceptionHandler extends RuntimeException {

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<Object> DataIntegrityViolationExceptionHandler() {
        Response response = new Response(409, "User is already registered", null);
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<Object> HttpMessageNotReadableExceptionHandler() {
        Response response = new Response(400, "Request only accept valid credentials", null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MalformedJwtException.class)
    public ResponseEntity<Object> MalformedJwtException() {
        Response response = new Response(500, "Jwt token is not valid", null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ExpiredJwtException.class)
    public ResponseEntity<Object> ExpiredJwtException() {
        Response response = new Response(500, "Session expired for user", null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = SignatureException.class)
    public ResponseEntity<Object> SignatureException() {
        Response response = new Response(500, "Token is not signed with valid signature", null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ClaimJwtException.class)
    public ResponseEntity<Object> ClaimJwtException() {
        Response response = new Response(500, "Claims present are not valid", null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MissingClaimException.class)
    public ResponseEntity<Object> MissingClaimException() {
        Response response = new Response(500, "Claims are missing", null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = EmptyAuthHeaderException.class)
    public ResponseEntity<Object> EmptyAuthHeaderException() {
        Response response = new Response(500, "Token cannot be null", null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
