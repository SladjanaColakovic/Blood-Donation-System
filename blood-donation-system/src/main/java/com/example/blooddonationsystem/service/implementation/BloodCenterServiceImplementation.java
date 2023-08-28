package com.example.blooddonationsystem.service.implementation;

import com.example.blooddonationsystem.dto.BloodCenterDTO;
import com.example.blooddonationsystem.dto.EditBloodCenterDTO;
import com.example.blooddonationsystem.model.BloodCenter;
import com.example.blooddonationsystem.model.User;
import com.example.blooddonationsystem.repository.BloodCenterRepository;
import com.example.blooddonationsystem.service.BloodCenterService;
import com.example.blooddonationsystem.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BloodCenterServiceImplementation implements BloodCenterService {

    @Autowired
    private BloodCenterRepository bloodCenterRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;
    @Override
    public BloodCenter addNewCenter(BloodCenterDTO newCenter) {
        BloodCenter center = modelMapper.map(newCenter, BloodCenter.class);
        User manager = userService.register(newCenter.getManager());
        center.setManager(manager);
        return bloodCenterRepository.save(center);
    }

    @Override
    public BloodCenter getManagerBloodCenter(String managerUsername) {
        User manager = userService.findByUsername(managerUsername);
        return bloodCenterRepository.findByManager(manager);
    }

    @Override
    public BloodCenter edit(EditBloodCenterDTO editBloodCenterDTO) {

        BloodCenter center = bloodCenterRepository.findById(editBloodCenterDTO.getId()).get();

        return bloodCenterRepository.save(editChangedCenterInfo(center, editBloodCenterDTO));
    }

    @Override
    public List<BloodCenter> getAll() {
        return bloodCenterRepository.findAll();
    }

    private BloodCenter editChangedCenterInfo(BloodCenter center, EditBloodCenterDTO editBloodCenterDTO){
        if(editBloodCenterDTO.getName() != null && !editBloodCenterDTO.getName().isBlank()){
            center.setName(editBloodCenterDTO.getName());
        }
        if(editBloodCenterDTO.getEmail() != null && !editBloodCenterDTO.getEmail().isBlank()){
            center.setEmail(editBloodCenterDTO.getEmail());
        }
        if(editBloodCenterDTO.getAddress() != null && !editBloodCenterDTO.getAddress().isBlank()){
            center.setAddress(editBloodCenterDTO.getAddress());
        }
        if(editBloodCenterDTO.getCity() != null && !editBloodCenterDTO.getCity().isBlank()){
            center.setCity(editBloodCenterDTO.getCity());
        }
        if(editBloodCenterDTO.getCountry() != null && !editBloodCenterDTO.getCountry().isBlank()){
            center.setCountry(editBloodCenterDTO.getCountry());
        }
        if(editBloodCenterDTO.getPhoneNumber() != null && !editBloodCenterDTO.getPhoneNumber().isBlank()){
            center.setPhoneNumber(editBloodCenterDTO.getPhoneNumber());
        }
        if(editBloodCenterDTO.getDescription() != null && !editBloodCenterDTO.getDescription().isBlank()){
            center.setDescription(editBloodCenterDTO.getDescription());
        }
        if(editBloodCenterDTO.getWorkingTimeFrom() != 0){
            center.setWorkingTimeFrom(editBloodCenterDTO.getWorkingTimeFrom());
        }
        if(editBloodCenterDTO.getWorkingTimeTo() != 0){
            center.setWorkingTimeTo(editBloodCenterDTO.getWorkingTimeTo());
        }
        if(editBloodCenterDTO.getCapacity() != 0){
            center.setCapacity(editBloodCenterDTO.getCapacity());
        }
        return center;
    }
}
