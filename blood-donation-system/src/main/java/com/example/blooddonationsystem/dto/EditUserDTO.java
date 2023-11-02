package com.example.blooddonationsystem.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EditUserDTO {

    private String username;
    private String address;
    private String city;
    private String country;
    private String phoneNumber;

}
