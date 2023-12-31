package com.example.blooddonationsystem.dto;

import com.example.blooddonationsystem.model.BloodCenter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
public class DonorAppointmentResponseDTO {

    private Long id;
    private LocalDateTime startDateTime;
    private BloodCenter center;
}
