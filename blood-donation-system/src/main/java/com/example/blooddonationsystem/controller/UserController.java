package com.example.blooddonationsystem.controller;

import com.example.blooddonationsystem.dto.ChangePasswordDTO;
import com.example.blooddonationsystem.dto.EditUserDTO;
import com.example.blooddonationsystem.dto.UserDTO;
import com.example.blooddonationsystem.exception.UserEmailExistsException;
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
        return new ResponseEntity<>(this.userService.register(newUser), HttpStatus.CREATED);
    }

    @GetMapping("/current/{username}")
    @PreAuthorize("hasAnyRole('DONOR', 'ADMIN', 'MANAGER')")
    public ResponseEntity<?> current(@PathVariable String username){
        return new ResponseEntity<>(userService.findByUsername(username), HttpStatus.OK);
    }

    @PutMapping("/edit")
    @PreAuthorize("hasAnyRole('DONOR', 'ADMIN', 'MANAGER')")
    public ResponseEntity<?> edit(@RequestBody EditUserDTO editUser){
        return new ResponseEntity<>(userService.edit(editUser), HttpStatus.OK);
    }

    @PutMapping("/changePassword")
    @PreAuthorize("hasAnyRole('DONOR', 'ADMIN', 'MANAGER')")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDTO changePassword){
        return new ResponseEntity<>(userService.changePassword(changePassword), HttpStatus.OK);
    }


}
