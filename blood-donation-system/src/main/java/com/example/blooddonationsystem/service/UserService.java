package com.example.blooddonationsystem.service;

import com.example.blooddonationsystem.dto.UserDTO;
import com.example.blooddonationsystem.model.User;

public interface UserService {
    public User findByUsername(String username);
    public User register(UserDTO userDTO);
}
