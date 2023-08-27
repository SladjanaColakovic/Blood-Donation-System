package com.example.blooddonationsystem.service;

import com.example.blooddonationsystem.dto.LoginRequest;
import com.example.blooddonationsystem.dto.LoginResponse;

public interface AuthenticationService {
    public LoginResponse login(LoginRequest loginRequest);
}
