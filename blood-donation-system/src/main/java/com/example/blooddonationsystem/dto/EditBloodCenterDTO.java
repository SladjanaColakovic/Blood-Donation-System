package com.example.blooddonationsystem.dto;

public class EditBloodCenterDTO {
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

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
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
}
