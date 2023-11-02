package com.example.blooddonationsystem.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter
@NoArgsConstructor
public class NewAppointmentDTO {
    private Long centerId;
    private String donorUsername;
    private LocalDateTime startDateTime;
}
