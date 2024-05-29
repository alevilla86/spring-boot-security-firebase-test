package com.alevilla86.firebase.security.test.firebase_security_test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alevilla86.firebase.security.test.firebase_security_test.dto.UserDto;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord.CreateRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping()
    public String addUser(@RequestBody UserDto userDto) {
        
        CreateRequest request = new CreateRequest();
        request.setEmail(userDto.getEmail());
        request.setPassword(userDto.getPassword());

        FirebaseAuth auth = FirebaseAuth.getInstance();

        try {
            auth.createUser(request);
        } catch (FirebaseAuthException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return "user created";
    }

    @GetMapping()
    public String getUser(Principal principal) {
        
        return "user: " + principal.getName();
    }
    
    
}
