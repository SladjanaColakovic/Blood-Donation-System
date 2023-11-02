package com.example.blooddonationsystem.controller;

import com.example.blooddonationsystem.dto.BloodCenterDTO;
import com.example.blooddonationsystem.dto.NewBloodCenterDTO;
import com.example.blooddonationsystem.dto.EditBloodCenterDTO;
import com.example.blooddonationsystem.model.BloodCenter;
import com.example.blooddonationsystem.service.BloodCenterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/center")
public class BloodCenterController {

    @Autowired
    private BloodCenterService bloodCenterService;

    @Autowired
    private ModelMapper mapper;

    @PostMapping(value = "/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> add(@RequestBody NewBloodCenterDTO newCenter) {
        BloodCenter bloodCenter = bloodCenterService.add(newCenter);
        return new ResponseEntity<>(mapper.map(bloodCenter, BloodCenterDTO.class), HttpStatus.OK);
    }

    @PutMapping(value = "/image")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> changeImage(@RequestPart("centerId") Long centerId,
                                         @RequestPart("image") MultipartFile image) {
        BloodCenter bloodCenter = bloodCenterService.changeImage(centerId, image);
        return new ResponseEntity<>(mapper.map(bloodCenter, BloodCenterDTO.class), HttpStatus.OK);
    }

    @GetMapping("/{managerUsername}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> getManagerBloodCenter(@PathVariable String managerUsername) {
        BloodCenter bloodCenter = bloodCenterService.getManagerBloodCenter(managerUsername);
        return new ResponseEntity<>(mapper.map(bloodCenter, BloodCenterDTO.class), HttpStatus.OK);
    }

    @PutMapping("/edit")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> edit(@RequestBody EditBloodCenterDTO centerEdit) {
        BloodCenter bloodCenter = bloodCenterService.edit(centerEdit);
        return new ResponseEntity<>(mapper.map(bloodCenter, BloodCenterDTO.class), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        List<BloodCenter> centers = bloodCenterService.getAll();
        return new ResponseEntity<>(centers.stream()
                .map(element -> mapper.map(element, BloodCenterDTO.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        BloodCenter bloodCenter = bloodCenterService.getById(id);
        return new ResponseEntity<>(mapper.map(bloodCenter, BloodCenterDTO.class), HttpStatus.OK);
    }

    @GetMapping("/searchSort")
    public ResponseEntity<?> getFreeBloodCenters(@RequestParam("sortBy") String sortBy,
                                                 @RequestParam("sortDirection") String sortDirection,
                                                 @RequestParam(value = "dateTime", required = false) LocalDateTime dateTime,
                                                 @RequestParam(value = "center", required = false) String center,
                                                 @RequestParam(value = "address", required = false) String address) {
        List<BloodCenter> centers = bloodCenterService.searchAndSortFreeCenters(sortBy, sortDirection, dateTime, center, address);
        return new ResponseEntity<>(centers.stream()
                .map(element -> mapper.map(element, BloodCenterDTO.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }
}
