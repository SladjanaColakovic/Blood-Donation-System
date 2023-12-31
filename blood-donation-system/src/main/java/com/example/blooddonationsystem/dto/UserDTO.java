package com.example.blooddonationsystem.dto;

import com.example.blooddonationsystem.enumeration.Gender;
import com.example.blooddonationsystem.enumeration.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private String confirmPassword;
    private String name;
    private String surname;
    private String address;
    private String city;
    private String country;
    private String phoneNumber;
    private String jmbg;
    private Gender gender;
    private Role role;
}
