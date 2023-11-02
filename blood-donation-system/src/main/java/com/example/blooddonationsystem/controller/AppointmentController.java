package com.example.blooddonationsystem.controller;

import com.example.blooddonationsystem.dto.AppointmentDTO;
import com.example.blooddonationsystem.dto.NewAppointmentDTO;
import com.example.blooddonationsystem.model.Appointment;
import com.example.blooddonationsystem.service.AppointmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(value = "/api/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private ModelMapper mapper;

    @PostMapping("/schedule")
    @PreAuthorize("hasRole('DONOR')")
    public ResponseEntity<?> schedule(@RequestBody NewAppointmentDTO newAppointment) {
        Appointment appointment = appointmentService.schedule(newAppointment);
        return new ResponseEntity<>(mapper.map(appointment, AppointmentDTO.class), HttpStatus.OK);
    }

    @GetMapping("/donor/{donorUsername}")
    @PreAuthorize("hasRole('DONOR')")
    public ResponseEntity<?> getPassedDonorAppointments(@PathVariable String donorUsername) {
        return new ResponseEntity<>(appointmentService.getPassedDonorAppointments(donorUsername), HttpStatus.OK);
    }

    @GetMapping("/manager/{managerUsername}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> getBloodCenterAppointments(@PathVariable String managerUsername) {
        return new ResponseEntity<>(appointmentService.getBloodCenterAppointments(managerUsername), HttpStatus.OK);
    }

    @GetMapping("/notPassed/{donorUsername}")
    @PreAuthorize("hasRole('DONOR')")
    public ResponseEntity<?> getNotPassedDonorAppointments(@PathVariable String donorUsername) {
        return new ResponseEntity<>(appointmentService.getNotPassedAppointments(donorUsername), HttpStatus.OK);
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
        return new ResponseEntity<>(appointmentService.searchAndSortDonorAppointments(donorUsername, sortBy, sortDirection, searchText, searchDate), HttpStatus.OK);
    }
}
