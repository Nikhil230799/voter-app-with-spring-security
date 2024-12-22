package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import com.example.common.Response;
import com.example.entity.Candidates;
import com.example.entity.Users;
import com.example.jwtutils.JwtService;
import com.example.repository.CandidatesReporsitory;
import com.example.repository.UsersRepository;
import com.example.securityconfig.CustomUserService;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// @CrossOrigin(origins = "*",allowedHeaders = {"Authorization", "Origin"}) 
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UsersController {

    private Response response;

    @Autowired
    private CandidatesReporsitory candidatesReporsitory;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private CustomUserService customUserService;

    @GetMapping("/test")
    public ResponseEntity<Response> test() {
        // public String teString() {

        response.setResponseCode(1);
        System.out.println("hello");
        response.setData("get");
        response.setResponseDesc("success");
        return new ResponseEntity<Response>(response, HttpStatus.OK);
        // return "jello";
    }

    @GetMapping("/getCandidatesList")
    public ResponseEntity<Response> getCandidatelist() {
        try {
            List<Candidates> list = candidatesReporsitory.findAll();
            response = new Response(200, "users List", list);
            return new ResponseEntity<Response>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setResponseCode(0);
            response.setData(e.getMessage());
            response.setResponseCode(500);
            return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/votecandidate")
    public ResponseEntity<Response> updateVotes(@RequestParam("cd_id") int cd_id,
            @RequestHeader(name = "Authorization") String token) {
        try {

            UserDetails user = customUserService.loadUserByUsername(jwtService.extractUsername(token));
            Users user1 = usersRepository.getUserByUsername(user.getUsername());

            Candidates cd = candidatesReporsitory.findById(cd_id).get();

            if (user1.isUsr_voteStatus()==false) {
                user1.setUsr_voteStatus(true);
                user1.setCandidates(cd);
                usersRepository.save(user1);
                response = new Response(200, "user update", "User voted successfully");
                return new ResponseEntity<Response>(response, HttpStatus.OK);

            } else {
                response = new Response(200, "Already voted", "You have already voted");
                return new ResponseEntity<Response>(response, HttpStatus.OK);
            }

        } catch (Exception e) {
            response.setResponseCode(0);
            response.setData(e.getMessage());
            response.setResponseCode(500);
            return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
