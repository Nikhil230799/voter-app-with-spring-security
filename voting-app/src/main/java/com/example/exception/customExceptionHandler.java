package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import java.util.*;

import com.example.common.Response;

@RestController
@ControllerAdvice
public class customExceptionHandler extends RuntimeException {

    @ExceptionHandler(UniqueConstraintViolationException.class)
    public ResponseEntity<Response> handleUniqueConstraintViolation(UniqueConstraintViolationException ex) {
        Map<String, String> mapResponse = new HashMap<>();
        mapResponse.put("field", ex.getFieldName());
        mapResponse.put("error", ex.getMessage());
        Response response = new Response(409, getLocalizedMessage(), mapResponse);
        return new ResponseEntity<Response>(response, HttpStatus.CONFLICT);
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

    @ExceptionHandler(value = UsernameNotFoundException.class)
    public ResponseEntity<Object> UsernameNotFoundException() {
        Response response = new Response(500, "User does not exists", null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
