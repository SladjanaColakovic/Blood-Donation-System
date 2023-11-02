package com.example.blooddonationsystem.dto;

import com.example.blooddonationsystem.enumeration.Gender;
import com.example.blooddonationsystem.enumeration.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class NewUserDTO {

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
