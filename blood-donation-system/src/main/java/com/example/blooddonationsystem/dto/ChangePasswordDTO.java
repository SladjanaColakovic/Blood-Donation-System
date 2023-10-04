package com.example.blooddonationsystem.dto;

public class ChangePasswordDTO {

    private String password;
    private String confirmPassword;
    private String oldPassword;

    private String username;

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
