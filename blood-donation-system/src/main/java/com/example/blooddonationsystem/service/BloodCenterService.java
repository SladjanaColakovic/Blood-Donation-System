package com.example.blooddonationsystem.service;
import com.example.blooddonationsystem.dto.NewBloodCenterDTO;
import com.example.blooddonationsystem.dto.EditBloodCenterDTO;
import com.example.blooddonationsystem.model.BloodCenter;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDateTime;
import java.util.List;

public interface BloodCenterService {
    public BloodCenter add(NewBloodCenterDTO newCenter);
    public BloodCenter changeImage(Long centerId, MultipartFile image);
    public BloodCenter getManagerBloodCenter(String managerUsername);
    public BloodCenter edit(EditBloodCenterDTO editBloodCenterDTO);
    public List<BloodCenter> getAll();
    public BloodCenter getById(Long id);
    public List<BloodCenter> searchAndSortFreeCenters(String sortBy, String sortDirection, LocalDateTime dateTime, String center, String address);
}
