package com.example.blooddonationsystem.service;

import com.example.blooddonationsystem.dto.NewAppointmentDTO;
import com.example.blooddonationsystem.dto.DonorAppointmentResponseDTO;
import com.example.blooddonationsystem.dto.ManagerAppointmentResponseDTO;
import com.example.blooddonationsystem.model.Appointment;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentService {
    public Appointment schedule(NewAppointmentDTO newAppointment);
    public List<DonorAppointmentResponseDTO> getPassedDonorAppointments(String donorUsername);
    public List<ManagerAppointmentResponseDTO> getBloodCenterAppointments(String managerUsername);
    public List<DonorAppointmentResponseDTO> getNotPassedAppointments(String donorUsername);
    public Boolean cancel(Long id);
    public List<DonorAppointmentResponseDTO> searchAndSortDonorAppointments(String donorUsername, String sortBy, String sortDirection, String searchText, LocalDate searchDate);

}
