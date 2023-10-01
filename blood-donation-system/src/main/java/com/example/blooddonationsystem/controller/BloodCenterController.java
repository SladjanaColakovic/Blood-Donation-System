package com.example.blooddonationsystem.controller;

import com.example.blooddonationsystem.dto.BloodCenterDTO;
import com.example.blooddonationsystem.dto.EditBloodCenterDTO;
import com.example.blooddonationsystem.model.BloodCenter;
import com.example.blooddonationsystem.service.BloodCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/api/center")
public class BloodCenterController {

    @Autowired
    private BloodCenterService bloodCenterService;

    @PostMapping(value = "/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> add(@RequestBody BloodCenterDTO newCenter) {
        return new ResponseEntity<>(bloodCenterService.add(newCenter), HttpStatus.OK);
    }

    @PutMapping(value = "/image")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> changeImage(@RequestPart("centerId") Long centerId,
                                         @RequestPart("image") MultipartFile image) {
        return new ResponseEntity<>(bloodCenterService.changeImage(centerId, image), HttpStatus.OK);
    }

    @GetMapping("/{managerUsername}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> getManagerBloodCenter(@PathVariable String managerUsername) {
        return new ResponseEntity<>(bloodCenterService.getManagerBloodCenter(managerUsername), HttpStatus.OK);
    }

    @PutMapping("/edit")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> edit(@RequestBody EditBloodCenterDTO centerEdit) {
        return new ResponseEntity<>(bloodCenterService.edit(centerEdit), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(bloodCenterService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return new ResponseEntity<>(bloodCenterService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/searchSort")
    public ResponseEntity<?> getFreeBloodCenters(@RequestParam("sortBy") String sortBy,
                                                 @RequestParam("sortDirection") String sortDirection,
                                                 @RequestParam(value = "dateTime", required = false) LocalDateTime dateTime,
                                                 @RequestParam(value = "center", required = false) String center,
                                                 @RequestParam(value = "address", required = false) String address) {
        return new ResponseEntity<>(bloodCenterService.searchAndSortFreeCenters(sortBy, sortDirection, dateTime, center, address), HttpStatus.OK);
    }
}
