package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.common.Response;
import com.example.entity.Candidates;
import com.example.repository.CandidatesReporsitory;
import java.util.*;

// @CrossOrigin(origins = "*",allowedHeaders = {"Authorization", "Origin"}) 
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UsersController {

    private Response response;

    @Autowired
    private CandidatesReporsitory candidatesReporsitory;

    @GetMapping("/test")
    public ResponseEntity<Response> teString() {
        // public String teString() {

        response.setResponseCode(1);
        System.out.println("hello");
        response.setData("get");
        response.setResponseDesc("success");

        return new ResponseEntity<Response>(response, HttpStatus.OK);
        // return "jello";
    }

    @GetMapping("/getCandidatesList")
    public  ResponseEntity<Response> getMethodName() {
        try {
            List<Candidates> list=candidatesReporsitory.findAll();
            response=new Response(200,"users List",list);
            return new ResponseEntity<Response>(response,HttpStatus.OK);

        } catch (Exception e) {
            response.setResponseCode(0);
            response.setData(e.getMessage());
            response.setResponseCode(500);
            return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
