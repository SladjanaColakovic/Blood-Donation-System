package com.example.blooddonationsystem.dto;

import com.example.blooddonationsystem.model.BloodCenter;
import com.example.blooddonationsystem.model.User;

import java.time.LocalDateTime;

public class ManagerAppointmentResponseDTO {
    private Long id;
    private LocalDateTime startDateTime;
    private BloodCenter center;
    private User donor;

    public Long getId() {
        return id;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public BloodCenter getCenter() {
        return center;
    }

    public User getDonor() {
        return donor;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public void setCenter(BloodCenter center) {
        this.center = center;
    }

    public void setDonor(User donor) {
        this.donor = donor;
    }
}
