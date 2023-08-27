package com.example.blooddonationsystem.dto;

public class LoginResponse {

    private String accessToken;
    private Long expiresIn;

    public LoginResponse() {
        this.accessToken = null;
        this.expiresIn = null;
    }

    public LoginResponse(String accessToken, long expiresIn) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

}
