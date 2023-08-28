package com.example.blooddonationsystem.service.implementation;

import com.example.blooddonationsystem.dto.BloodCenterDTO;
import com.example.blooddonationsystem.model.BloodCenter;
import com.example.blooddonationsystem.model.User;
import com.example.blooddonationsystem.repository.BloodCenterRepository;
import com.example.blooddonationsystem.service.BloodCenterService;
import com.example.blooddonationsystem.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
