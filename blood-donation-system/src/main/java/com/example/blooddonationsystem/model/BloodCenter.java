package com.example.blooddonationsystem.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
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

    @OneToMany(mappedBy = "center", fetch = FetchType.EAGER)
    private Set<Appointment> appointments;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getWorkingTimeFrom() {
        return workingTimeFrom;
    }

    public int getWorkingTimeTo() {
        return workingTimeTo;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getDescription() {
        return description;
    }

    public User getManager() {
        return manager;
    }

    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setWorkingTimeFrom(int workingTimeFrom) {
        this.workingTimeFrom = workingTimeFrom;
    }

    public void setWorkingTimeTo(int workingTimeTo) {
        this.workingTimeTo = workingTimeTo;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }
}
