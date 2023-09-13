package com.example.blooddonationsystem.controller;

import ch.qos.logback.core.boolex.EvaluationException;
import com.example.blooddonationsystem.dto.AppointmentDTO;
import com.example.blooddonationsystem.model.Appointment;
import com.example.blooddonationsystem.service.AppointmentService;
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

    @GetMapping("/manager/{managerUsername}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> getBloodCenterAppointments(@PathVariable  String managerUsername){
        return  new ResponseEntity<>(appointmentService.getBloodCenterAppointments(managerUsername), HttpStatus.OK);
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

    @GetMapping("/sort")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> sortDonorAppointments(@RequestParam("sortBy") String sortBy,
                                                   @RequestParam("sortDirection") String sortDirection,
                                                   @RequestParam("donorUsername") String donorUsername,
                                                   @RequestParam(value = "searchText", required = false) String searchText,
                                                   @RequestParam(value = "searchDate", required = false) LocalDate searchDate){
        return new ResponseEntity<>(appointmentService.sortDonorAppointments(donorUsername, sortBy, sortDirection, searchText, searchDate), HttpStatus.OK);
    }
}
