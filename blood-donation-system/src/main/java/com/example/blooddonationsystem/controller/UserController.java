package com.example.blooddonationsystem.controller;

import com.example.blooddonationsystem.dto.ChangePasswordDTO;
import com.example.blooddonationsystem.dto.EditUserDTO;
import com.example.blooddonationsystem.dto.NewUserDTO;
import com.example.blooddonationsystem.dto.UserDTO;
import com.example.blooddonationsystem.model.User;
import com.example.blooddonationsystem.service.UserService;
import org.modelmapper.ModelMapper;
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

    @Autowired
    private ModelMapper mapper;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody NewUserDTO newUser){
        User user = userService.register(newUser);
        return new ResponseEntity<>(mapper.map(user, UserDTO.class), HttpStatus.CREATED);
    }

    @GetMapping("/current/{username}")
    @PreAuthorize("hasAnyRole('DONOR', 'ADMIN', 'MANAGER')")
    public ResponseEntity<?> current(@PathVariable String username){
        User user = userService.findByUsername(username);
        return new ResponseEntity<>(mapper.map(user, UserDTO.class), HttpStatus.OK);
    }

    @PutMapping("/edit")
    @PreAuthorize("hasAnyRole('DONOR', 'ADMIN', 'MANAGER')")
    public ResponseEntity<?> edit(@RequestBody EditUserDTO editUser){
        User user = userService.edit(editUser);
        return new ResponseEntity<>(mapper.map(user, UserDTO.class), HttpStatus.OK);
    }

    @PutMapping("/changePassword")
    @PreAuthorize("hasAnyRole('DONOR', 'ADMIN', 'MANAGER')")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDTO changePassword){
        User user = userService.changePassword(changePassword);
        return new ResponseEntity<>(mapper.map(user, UserDTO.class), HttpStatus.OK);
    }


}
