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
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO){
        User user = this.userService.register(userDTO);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> userFunc(){
        return new ResponseEntity<>("User func", HttpStatus.OK);
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> adminFunc(){
        return new ResponseEntity<>("Admin func", HttpStatus.OK);
    }

    @GetMapping("/current/{username}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'MANAGER')")
    public ResponseEntity<?> current(@PathVariable String username){
        return new ResponseEntity<>(userService.findByUsername(username), HttpStatus.OK);
    }

    @PostMapping("/edit")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'MANAGER')")
    public ResponseEntity<?> edit(@RequestBody EditUserDTO editUserDTO){
        User user = userService.edit(editUserDTO);
        if(user == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


}
