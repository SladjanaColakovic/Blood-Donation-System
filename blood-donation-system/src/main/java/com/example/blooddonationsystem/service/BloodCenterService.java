package com.example.blooddonationsystem.service;

import com.example.blooddonationsystem.dto.BloodCenterDTO;
import com.example.blooddonationsystem.dto.EditBloodCenterDTO;
import com.example.blooddonationsystem.model.BloodCenter;

import java.time.LocalDateTime;
import java.util.List;

public interface BloodCenterService {
    public BloodCenter addNewCenter(BloodCenterDTO newCenter);
    public BloodCenter getManagerBloodCenter(String managerUsername);
    public BloodCenter edit(EditBloodCenterDTO editBloodCenterDTO);
    public List<BloodCenter> getAll();
    public BloodCenter getById(Long id);
    public List<BloodCenter> getFreeBloodCenters(LocalDateTime dateTime);
}
