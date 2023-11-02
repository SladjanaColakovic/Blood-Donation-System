package com.example.blooddonationsystem.dto;

import com.example.blooddonationsystem.model.BloodCenter;
import com.example.blooddonationsystem.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter
@NoArgsConstructor
public class ManagerAppointmentResponseDTO {

    private Long id;
    private LocalDateTime startDateTime;
    private BloodCenter center;
    private User donor;

}
