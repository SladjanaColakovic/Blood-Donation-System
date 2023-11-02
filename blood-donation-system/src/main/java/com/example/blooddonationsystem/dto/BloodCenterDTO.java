package com.example.blooddonationsystem.dto;

import com.example.blooddonationsystem.model.Image;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class BloodCenterDTO {
    private Long id;
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
    private UserDTO manager;
    private Image image;
}
