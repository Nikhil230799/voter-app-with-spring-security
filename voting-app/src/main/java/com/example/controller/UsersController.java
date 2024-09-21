package com.example.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UsersController {

    @GetMapping("/test")
    public String teString() {
    
        return new String("hello");
    }

}
