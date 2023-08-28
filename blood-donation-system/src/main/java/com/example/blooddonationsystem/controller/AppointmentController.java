package com.example.blooddonationsystem.controller;

import com.example.blooddonationsystem.dto.AppointmentDTO;
import com.example.blooddonationsystem.model.Appointment;
import com.example.blooddonationsystem.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/schedule")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> scheduleAppointment(@RequestBody AppointmentDTO newAppointment){
        Appointment appointment = appointmentService.schedule(newAppointment);
        if(appointment == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity<>(appointment, HttpStatus.OK);
    }

    @GetMapping("/donor/{donorUsername}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getDonorAppointments(@PathVariable String donorUsername){
        return new ResponseEntity<>(appointmentService.getDonorAppointments(donorUsername), HttpStatus.OK);
    }
}
