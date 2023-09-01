package com.example.blooddonationsystem.controller;

import com.example.blooddonationsystem.dto.BloodCenterDTO;
import com.example.blooddonationsystem.dto.EditBloodCenterDTO;
import com.example.blooddonationsystem.service.BloodCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/api/center")
public class BloodCenterController {

    @Autowired
    private BloodCenterService bloodCenterService;

    @PostMapping(value = "/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addNewCenter(@RequestPart("center") BloodCenterDTO newCenter,
                                          @RequestPart("image") MultipartFile image){
        return new ResponseEntity<>(bloodCenterService.addNewCenter(newCenter, image), HttpStatus.OK);
    }

    @GetMapping("/{managerUsername}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> getManagerBloodCenter(@PathVariable String managerUsername){
        return new ResponseEntity<>(bloodCenterService.getManagerBloodCenter(managerUsername), HttpStatus.OK);
    }
    @PutMapping("/edit")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> editCenter(@RequestBody EditBloodCenterDTO editBloodCenterDTO){
        return new ResponseEntity<>(bloodCenterService.edit(editBloodCenterDTO), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(bloodCenterService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/free/{dateTime}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getFreeBloodCenters(@PathVariable LocalDateTime dateTime){
        return new ResponseEntity<>(bloodCenterService.getFreeBloodCenters(dateTime), HttpStatus.OK);
    }
}
