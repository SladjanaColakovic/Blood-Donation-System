package com.example.blooddonationsystem.validation;

import com.example.blooddonationsystem.dto.LoginRequest;

public class AuthenticationValidation {

    public static boolean isLoginInvalid(LoginRequest loginRequest) {
        return (loginRequest.getUsername() == null || loginRequest.getUsername().equals("") ||
                loginRequest.getPassword() == null || loginRequest.getPassword().equals("")
        );
    }
}
