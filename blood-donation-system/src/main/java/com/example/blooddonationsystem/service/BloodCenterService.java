package com.example.blooddonationsystem.service;

import com.example.blooddonationsystem.dto.BloodCenterDTO;
import com.example.blooddonationsystem.dto.EditBloodCenterDTO;
import com.example.blooddonationsystem.model.BloodCenter;

public interface BloodCenterService {
    public BloodCenter addNewCenter(BloodCenterDTO newCenter);
    public BloodCenter getManagerBloodCenter(String managerUsername);
    public BloodCenter edit(EditBloodCenterDTO editBloodCenterDTO);
}
