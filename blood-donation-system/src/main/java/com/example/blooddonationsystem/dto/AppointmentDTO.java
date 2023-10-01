package com.example.blooddonationsystem.dto;

import java.time.LocalDateTime;

public class AppointmentDTO {
    private Long centerId;
    private String donorUsername;
    private LocalDateTime startDateTime;

    public Long getCenterId() {
        return centerId;
    }

    public String getDonorUsername() {
        return donorUsername;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setCenterId(Long centerId) {
        this.centerId = centerId;
    }

    public void setDonorUsername(String donorUsername) {
        this.donorUsername = donorUsername;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }
}
