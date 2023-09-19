package com.example.blooddonationsystem.controller;

import com.example.blooddonationsystem.dto.AppointmentDTO;
import com.example.blooddonationsystem.dto.DonorAppointmentResponseDTO;
import com.example.blooddonationsystem.dto.ManagerAppointmentResponseDTO;
import com.example.blooddonationsystem.model.Appointment;
import com.example.blooddonationsystem.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/api/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/schedule")
    @PreAuthorize("hasRole('DONOR')")
    public ResponseEntity<?> schedule(@RequestBody AppointmentDTO newAppointment) {
        Appointment scheduledAppointment = appointmentService.schedule(newAppointment);
        if (scheduledAppointment == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(scheduledAppointment, HttpStatus.OK);
    }

    @GetMapping("/donor/{donorUsername}")
    @PreAuthorize("hasRole('DONOR')")
    public ResponseEntity<?> getPassedDonorAppointments(@PathVariable String donorUsername) {
        List<DonorAppointmentResponseDTO> passedDonorAppointments = appointmentService.getPassedDonorAppointments(donorUsername);
        if (passedDonorAppointments == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(passedDonorAppointments, HttpStatus.OK);
    }

    @GetMapping("/manager/{managerUsername}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> getBloodCenterAppointments(@PathVariable String managerUsername) {
        List<ManagerAppointmentResponseDTO> appointments = appointmentService.getBloodCenterAppointments(managerUsername);
        if (appointments == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @GetMapping("/notPassed/{donorUsername}")
    @PreAuthorize("hasRole('DONOR')")
    public ResponseEntity<?> getNotPassedDonorAppointments(@PathVariable String donorUsername) {
        List<DonorAppointmentResponseDTO> notPassedAppointments = appointmentService.getNotPassedAppointments(donorUsername);
        if (notPassedAppointments == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(notPassedAppointments, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('DONOR')")
    public ResponseEntity<?> cancel(@PathVariable Long id) {
        if (appointmentService.cancel(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/searchSort")
    @PreAuthorize("hasRole('DONOR')")
    public ResponseEntity<?> sortAndSearchDonorAppointments(@RequestParam("sortBy") String sortBy,
                                                            @RequestParam("sortDirection") String sortDirection,
                                                            @RequestParam("donorUsername") String donorUsername,
                                                            @RequestParam(value = "searchText", required = false) String searchText,
                                                            @RequestParam(value = "searchDate", required = false) LocalDate searchDate) {
        List<DonorAppointmentResponseDTO> donorAppointments = appointmentService.searchAndSortDonorAppointments(donorUsername, sortBy, sortDirection, searchText, searchDate);
        if (donorAppointments == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(donorAppointments, HttpStatus.OK);
    }
}
