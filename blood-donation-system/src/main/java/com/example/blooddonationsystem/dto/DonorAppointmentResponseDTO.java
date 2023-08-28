package com.example.blooddonationsystem.dto;

import com.example.blooddonationsystem.model.BloodCenter;

import java.time.LocalDateTime;

public class DonorAppointmentResponseDTO {

    private Long id;
    private LocalDateTime startDateTime;
    private BloodCenter center;

    public Long getId() {
        return id;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public BloodCenter getCenter() {
        return center;
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
}
