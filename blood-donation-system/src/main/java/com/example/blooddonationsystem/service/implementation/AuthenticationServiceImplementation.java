package com.example.blooddonationsystem.service.implementation;

import com.example.blooddonationsystem.dto.LoginRequest;
import com.example.blooddonationsystem.dto.LoginResponse;
import com.example.blooddonationsystem.model.User;
import com.example.blooddonationsystem.security.util.TokenUtils;
import com.example.blooddonationsystem.service.AuthenticationService;
import com.example.blooddonationsystem.service.UserService;
import com.example.blooddonationsystem.validation.AuthenticationValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImplementation implements AuthenticationService {

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;
    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        if(AuthenticationValidation.isLoginInvalid(loginRequest)){
            return null;
        }
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(), loginRequest.getPassword()));

        User user = userService.findByUsername(loginRequest.getUsername());
        String jwt = tokenUtils.generateToken(user);
        int expiresIn = tokenUtils.getExpiredIn();

        return new LoginResponse(jwt, expiresIn);
    }
}
