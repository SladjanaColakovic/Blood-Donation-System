package com.example.blooddonationsystem.controller;

import com.example.blooddonationsystem.dto.EditUserDTO;
import com.example.blooddonationsystem.dto.UserDTO;
import com.example.blooddonationsystem.model.User;
import com.example.blooddonationsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO newUser){
        User registeredUser = this.userService.register(newUser);
        if (registeredUser == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @GetMapping("/current/{username}")
    @PreAuthorize("hasAnyRole('DONOR', 'ADMIN', 'MANAGER')")
    public ResponseEntity<?> current(@PathVariable String username){
        User current = userService.findByUsername(username);
        if(current == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(current, HttpStatus.OK);
    }

    @PutMapping("/edit")
    @PreAuthorize("hasAnyRole('DONOR', 'ADMIN', 'MANAGER')")
    public ResponseEntity<?> edit(@RequestBody EditUserDTO editUser){
        User editingUser = userService.edit(editUser);
        if(editingUser == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(editingUser, HttpStatus.OK);
    }


}
