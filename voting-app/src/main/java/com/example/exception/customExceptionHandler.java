package com.example.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.example.common.Response;

@RestController
@ControllerAdvice
public class customExceptionHandler extends RuntimeException {
    
    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<Object> DataIntegrityViolationExceptionHandler(){
        Response response =new Response(409,"User is already registered", null);
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
    
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<Object> HttpMessageNotReadableExceptionHandler(){
        Response response =new Response(400,"Request only accept valid credentials", null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    
    
    

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    // private String message;

    // public customExceptionHandler() {}

    // public customExceptionHandler(String msg) {
    //     super(msg);
    //     this.message = msg;
    // }
}
