package com.example.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.common.Response;

import com.example.dto.UserDetails;
import com.example.entity.Users;
import com.example.repository.UsersRepository;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UsersRepository usersRepository;
    Response response = null;

    @PostMapping("/register")
    public ResponseEntity<Response> registerUser(@RequestBody @Valid Users user, BindingResult bindingResult) {

        System.out.println(user);

        if (bindingResult.hasFieldErrors()) {
            Map<String, String> map = new HashMap<>();
            bindingResult
                    .getFieldErrors()
                    .stream()
                    .forEach(f -> map.put(f.getField(), f.getDefaultMessage()));
            response = new Response(400, "Invalid Inputs", map);
            return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);

        }

        usersRepository.save(user);
        response = new Response(202, "User registered successfully", user);
        return new ResponseEntity<Response>(response, HttpStatus.ACCEPTED);

    }

    @PostMapping("/signin")
    public ResponseEntity<Response> signin(@RequestBody @Valid UserDetails userDetails, BindingResult bindingResult) {

        if (bindingResult.hasFieldErrors()) {
            Map<String, String> map = new HashMap<>();
            bindingResult
                    .getFieldErrors()
                    .stream()
                    .forEach(f -> map.put(f.getField(), f.getDefaultMessage()));
            response = new Response(400, "Invalid Inputs", map);
            return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
        }
        response = new Response(400, "login successfully", "jwt token");


        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping("/test")
    public String teString() {
        return new String("hello");
    }

}
