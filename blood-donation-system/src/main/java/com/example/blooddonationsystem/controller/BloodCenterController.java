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
        BloodCenter registeredCenter = bloodCenterService.add(newCenter);
        if (registeredCenter == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(registeredCenter, HttpStatus.OK);
    }

    @PutMapping(value = "/image")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> changeImage(@RequestPart("centerId") Long centerId,
                                         @RequestPart("image") MultipartFile image) {
        BloodCenter center = bloodCenterService.changeImage(centerId, image);
        if (center == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(center, HttpStatus.OK);
    }

    @GetMapping("/{managerUsername}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> getManagerBloodCenter(@PathVariable String managerUsername) {
        BloodCenter managerBloodCenter = bloodCenterService.getManagerBloodCenter(managerUsername);
        if (managerBloodCenter == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(managerBloodCenter, HttpStatus.OK);
    }

    @PutMapping("/edit")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> edit(@RequestBody EditBloodCenterDTO centerEdit) {
        BloodCenter editingCenter = bloodCenterService.edit(centerEdit);
        if (editingCenter == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(editingCenter, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(bloodCenterService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        BloodCenter center = bloodCenterService.getById(id);
        if (center == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(center, HttpStatus.OK);
    }

    @GetMapping("/searchSort")
    public ResponseEntity<?> getFreeBloodCenters(@RequestParam("sortBy") String sortBy,
                                                 @RequestParam("sortDirection") String sortDirection,
                                                 @RequestParam(value = "dateTime", required = false) LocalDateTime dateTime,
                                                 @RequestParam(value = "center", required = false) String center,
                                                 @RequestParam(value = "address", required = false) String address) {
        List<BloodCenter> bloodCenters = bloodCenterService.searchAndSortCenters(sortBy, sortDirection, dateTime, center, address);
        if (bloodCenters == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(bloodCenters, HttpStatus.OK);
    }
}
