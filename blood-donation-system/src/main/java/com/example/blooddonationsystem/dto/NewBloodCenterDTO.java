package com.example.blooddonationsystem.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class NewBloodCenterDTO {
    private String name;
    private String email;
    private String address;
    private String city;
    private String country;
    private String phoneNumber;
    private int workingTimeFrom;
    private int workingTimeTo;
    private int capacity;
    private String description;
    private NewUserDTO manager;

}
