package com.example.blooddonationsystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class BloodCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String country;
    @Column(nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private int workingTimeFrom;
    @Column(nullable = false)
    private int workingTimeTo;
    @Column(nullable = false)
    private int capacity;
    @Column(nullable = false, length = 1024)
    private String description;
    @OneToOne
    private User manager;

    @OneToOne(cascade = CascadeType.ALL)
    private Image image;

    @OneToMany(mappedBy = "center", fetch = FetchType.EAGER)
    private Set<Appointment> appointments;
}
