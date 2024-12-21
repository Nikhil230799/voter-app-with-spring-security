package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.common.Response;
import com.example.entity.Candidates;
import com.example.entity.Users;
import com.example.repository.CandidatesReporsitory;
import com.example.repository.UsersRepository;

@RestController
@RequestMapping("/admin")
public class AdminController {

    Response response = null;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private CandidatesReporsitory candidatesReporsitory;

    @GetMapping("/test")
    public ResponseEntity<Response> teString() {
        response = new Response(200, "res", "hellow");
        return new ResponseEntity<Response>(response, HttpStatus.ACCEPTED);

    }

    @GetMapping("/userList")
    public ResponseEntity<Response> getUserList() {
        try {
            List<Users> userList = usersRepository.findAll();
            response = new Response(202, "List of all users", userList);
            return new ResponseEntity<Response>(response, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            response = new Response(500, e.getMessage(), e);
            return new ResponseEntity<Response>(response, HttpStatus.NOT_FOUND);
        }

    }
    
    @GetMapping("/getCandidatesList")
    public ResponseEntity<Response> getMethodName() {
        try {
            List<Candidates> list = candidatesReporsitory.findAll();
            response = new Response(200, "users List", list);
            return new ResponseEntity<Response>(response, HttpStatus.OK);
        }
        catch (Exception e) {
            response.setResponseCode(0);
            response.setData(e.getMessage());
            response.setResponseCode(500);
            return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
