package com.example.blooddonationsystem.service;

import com.example.blooddonationsystem.dto.ChangePasswordDTO;
import com.example.blooddonationsystem.dto.EditUserDTO;
import com.example.blooddonationsystem.dto.NewUserDTO;
import com.example.blooddonationsystem.model.User;

public interface UserService {
    public User findByUsername(String username);
    public User register(NewUserDTO newUser);
    public User edit(EditUserDTO userDTO);
    public User changePassword(ChangePasswordDTO newPassword);
}
