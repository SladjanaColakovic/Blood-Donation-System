package com.example.blooddonationsystem.service;
import com.example.blooddonationsystem.dto.BloodCenterDTO;
import com.example.blooddonationsystem.dto.EditBloodCenterDTO;
import com.example.blooddonationsystem.model.BloodCenter;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDateTime;
import java.util.List;

public interface BloodCenterService {
    public BloodCenter addNewCenter(BloodCenterDTO newCenter);
    public BloodCenter changeImage(Long centerId, MultipartFile image);
    public BloodCenter getManagerBloodCenter(String managerUsername);
    public BloodCenter edit(EditBloodCenterDTO editBloodCenterDTO);
    public List<BloodCenter> getAll();
    public BloodCenter getById(Long id);
    public List<BloodCenter> getFreeBloodCenters(String sortBy, String sortDirection, LocalDateTime dateTime, String center, String address);
}
