package com.example.blooddonationsystem.service.implementation;

import com.example.blooddonationsystem.dto.AppointmentDTO;
import com.example.blooddonationsystem.dto.BloodCenterDTO;
import com.example.blooddonationsystem.dto.EditBloodCenterDTO;
import com.example.blooddonationsystem.model.Appointment;
import com.example.blooddonationsystem.model.BloodCenter;
import com.example.blooddonationsystem.model.Image;
import com.example.blooddonationsystem.model.User;
import com.example.blooddonationsystem.repository.BloodCenterRepository;
import com.example.blooddonationsystem.service.BloodCenterService;
import com.example.blooddonationsystem.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class BloodCenterServiceImplementation implements BloodCenterService {

    @Autowired
    private BloodCenterRepository bloodCenterRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;
   /* @Override
    public BloodCenter addNewCenter(BloodCenterDTO newCenter, MultipartFile image)  {
        BloodCenter center = modelMapper.map(newCenter, BloodCenter.class);
        User manager = userService.register(newCenter.getManager());
        Image centerImage = new Image();
        try {
            centerImage = new Image(image.getOriginalFilename(), image.getContentType(), image.getBytes());
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
        center.setImage(centerImage);
        center.setManager(manager);
        return bloodCenterRepository.save(center);
    }*/

    @Override
    public BloodCenter addNewCenter(BloodCenterDTO newCenter) {
        BloodCenter center = modelMapper.map(newCenter, BloodCenter.class);
        User manager = userService.register(newCenter.getManager());
        center.setManager(manager);
        return bloodCenterRepository.save(center);
    }

    @Override
    public BloodCenter changeImage(Long centerId, MultipartFile image) {
        BloodCenter center = bloodCenterRepository.findById(centerId).get();
        Image centerImage = new Image();
        try {
            centerImage = new Image(image.getOriginalFilename(), image.getContentType(), image.getBytes());
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
        center.setImage(centerImage);
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

    @Override
    public BloodCenter getById(Long id) {
        return bloodCenterRepository.findById(id).get();
    }

    @Override
    public List<BloodCenter> getFreeBloodCenters(String sortBy, String sortDirection, LocalDateTime dateTime, String center, String address) {
        List<BloodCenter> centers = new ArrayList<>();
        if(sortBy.equals("center")){
            if(sortDirection.equals("ascending")) {
                centers = bloodCenterRepository.searchBloodCenters(center, address, Sort.by(Sort.Direction.ASC, "name"));
            }else{
                centers = bloodCenterRepository.searchBloodCenters(center, address, Sort.by(Sort.Direction.DESC, "name"));
            }
        }
        if(sortBy.equals("address")){
            if(sortDirection.equals("ascending")){
                centers = bloodCenterRepository.searchBloodCenters(center, address, Sort.by(Sort.Direction.ASC, "address")
                        .and(Sort.by(Sort.Direction.ASC, "city")
                                .and(Sort.by(Sort.Direction.ASC, "country"))));
            }else{
                centers = bloodCenterRepository.searchBloodCenters(center, address, Sort.by(Sort.Direction.DESC, "address")
                        .and(Sort.by(Sort.Direction.DESC, "city")
                                .and(Sort.by(Sort.Direction.DESC, "country"))));
            }
        }
        if(dateTime != null){
            List<BloodCenter> freeBloodCenters = new ArrayList<>();
            for (BloodCenter bloodCenter: centers){
                int overlappingAppointments = countOverlappingAppointments(bloodCenter.getAppointments(), dateTime);
                if(!isCapacityExceed(bloodCenter.getCapacity(), overlappingAppointments)){
                    freeBloodCenters.add(bloodCenter);
                }
            }
            return  freeBloodCenters;
        }
        return centers;
    }

    private Boolean isCapacityExceed(int allowedCapacity, int overlappingAppointments){
        if(overlappingAppointments >= allowedCapacity){
            return  true;
        }
        return false;
    }

    private int countOverlappingAppointments(Set<Appointment> existingAppointments,LocalDateTime newAppointmentTime){
        int overlappingAppointments = 0;
        for(Appointment existingAppointment: existingAppointments){
            if(isOverlapping(existingAppointment.getStartDateTime(), newAppointmentTime)){
                overlappingAppointments++;
            }
        }
        return  overlappingAppointments;
    }

    private Boolean isOverlapping(LocalDateTime existingAppointment, LocalDateTime newAppointmnet){
        if((newAppointmnet.isBefore(existingAppointment.plusMinutes(30))
                || newAppointmnet.isEqual(existingAppointment.plusMinutes(30))
        ) &&
                (existingAppointment.isBefore(newAppointmnet.plusMinutes(30))
                        || existingAppointment.isEqual(newAppointmnet.plusMinutes(30))
                )){
            return true;
        }
        return false;
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
