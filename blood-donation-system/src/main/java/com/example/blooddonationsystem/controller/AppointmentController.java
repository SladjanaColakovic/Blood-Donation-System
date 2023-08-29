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

    @GetMapping("/manager/{centerId}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> getBloodCenterAppointments(@PathVariable  Long centerId){
        System.out.println("-----------------------" + centerId);
        return  new ResponseEntity<>(appointmentService.getBloodCenterAppointments(centerId), HttpStatus.OK);
    }

    @GetMapping("/notPassed/{donorUsername}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getNotPassedAppointments(@PathVariable String donorUsername){
        return  new ResponseEntity<>(appointmentService.getNotPassedAppointments(donorUsername), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> cancel(@PathVariable Long id){
        if(appointmentService.cancel(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
