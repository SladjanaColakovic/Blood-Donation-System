package com.example.blooddonationsystem.controller;

import com.example.blooddonationsystem.dto.LoginRequest;
import com.example.blooddonationsystem.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        System.out.println(loginRequest.getPassword());
        return new ResponseEntity<>(authenticationService.login(loginRequest), HttpStatus.OK);
    }
}
