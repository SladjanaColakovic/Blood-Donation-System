package com.example.blooddonationsystem.service;

import com.example.blooddonationsystem.dto.AppointmentDTO;
import com.example.blooddonationsystem.dto.DonorAppointmentResponseDTO;
import com.example.blooddonationsystem.model.Appointment;

import java.util.List;

public interface AppointmentService {
    public Appointment schedule(AppointmentDTO newAppointment);
    public List<DonorAppointmentResponseDTO> getDonorAppointments(String donorUsername);
}