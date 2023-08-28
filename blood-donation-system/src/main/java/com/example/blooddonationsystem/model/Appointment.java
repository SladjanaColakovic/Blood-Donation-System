package com.example.blooddonationsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

@Entity
@SQLDelete(sql = "UPDATE appointment SET canceled = true WHERE id = ?")
@Where(clause = "canceled = false")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime startDateTime;

    @ManyToOne
    @JsonIgnore
    private User donor;

    @ManyToOne
    @JsonIgnore
    private BloodCenter center;

    private Boolean canceled;

    public Long getId() {
        return id;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public User getDonor() {
        return donor;
    }

    public BloodCenter getCenter() {
        return center;
    }

    public Boolean getCanceled() {
        return canceled;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public void setDonor(User donor) {
        this.donor = donor;
    }

    public void setCenter(BloodCenter center) {
        this.center = center;
    }

    public void setCanceled(Boolean canceled) {
        this.canceled = canceled;
    }
}
