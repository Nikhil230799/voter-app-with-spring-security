package com.example.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.common.Response;

// @CrossOrigin(origins = "*",allowedHeaders = {"Authorization", "Origin"}) 
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UsersController {


     private Response response=new Response();

    @GetMapping("/test")
     public  ResponseEntity<Response> teString() {
    // public  String teString() {

        response.setResponseCode(1);
        System.out.println("hello");
        response.setData("get");
        response.setResponseDesc("success");

        return new ResponseEntity<Response>(response,HttpStatus.OK);
        // return "jello";
    }

    @GetMapping("/get")
    public String getMethodName( ) {
        return new String();
    }
    

}
