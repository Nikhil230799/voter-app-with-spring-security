package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.common.Response;

@RestController
@RequestMapping("/user")
public class UsersController {


     private Response response=new Response();

    @GetMapping("/test")
    public ResponseEntity<Response> teString() {

        response.setResponseCode(1);
        System.out.println("hello");
        response.setData("get");
        response.setResponseDesc("success");

        return new ResponseEntity<Response>(response,HttpStatus.BANDWIDTH_LIMIT_EXCEEDED);
        
    }

}
