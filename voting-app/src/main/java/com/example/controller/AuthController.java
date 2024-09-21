package com.example.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.common.Response;

import com.example.dto.MyUserDetails;
import com.example.entity.Users;
import com.example.jwtutils.JwtService;
import com.example.repository.UsersRepository;
import com.example.securityconfig.CustomUserDetails;
import com.example.securityconfig.CustomUserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UsersRepository usersRepository;
    Response response = null;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserService customUserService;

    @Autowired
    private AuthenticationManager authenticationManager;

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
        System.out.println(user.getUsr_password());
        user.setUsr_password(passwordEncoder.encode(user.getUsr_password()));
        System.out.println(user.getUsr_password());

        usersRepository.save(user);
        response = new Response(202, "User registered successfully", user);
        return new ResponseEntity<Response>(response, HttpStatus.ACCEPTED);

    }

    @PostMapping("/dologin")
    public ResponseEntity<Response> signin(@RequestBody @Valid MyUserDetails userDetails, BindingResult bindingResult) {

        UserDetails userDetailsauth = null;
        if (bindingResult.hasFieldErrors()) {
            Map<String, String> map = new HashMap<>();
            bindingResult
                    .getFieldErrors()
                    .stream()
                    .forEach(f -> map.put(f.getField(), f.getDefaultMessage()));
            response = new Response(400, "Invalid Inputs", map);
            return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
        }
        Authentication auth = null;
        try {
            auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword()));

            userDetailsauth = customUserService.loadUserByUsername(userDetails.getUsername());

        } catch (AuthenticationException e) {
            return new ResponseEntity<>(new Response(401, "User Details are Invalid", e.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
        System.out.println(userDetailsauth);
        if (auth.isAuthenticated()) {
            String jwtToken = jwtService.generateToken(userDetailsauth);

            response = new Response(200, "login success", jwtToken);

        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/test")
    public String teString() {
        return new String("hello");
    }

}
