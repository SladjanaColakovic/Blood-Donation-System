package com.example.blooddonationsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
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

    private static int duration = 30;

    public static int getDuration() {
        return duration;
    }

    public Boolean isOverlapping(LocalDateTime newAppointment) {
        return (newAppointment.isBefore(startDateTime.plusMinutes(duration)) &&
                startDateTime.isBefore(newAppointment.plusMinutes(duration)));
    }
}
