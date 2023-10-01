package com.example.blooddonationsystem.service.implementation;

import com.example.blooddonationsystem.dto.BloodCenterDTO;
import com.example.blooddonationsystem.dto.EditBloodCenterDTO;
import com.example.blooddonationsystem.exception.*;
import com.example.blooddonationsystem.model.Appointment;
import com.example.blooddonationsystem.model.BloodCenter;
import com.example.blooddonationsystem.model.Image;
import com.example.blooddonationsystem.model.User;
import com.example.blooddonationsystem.repository.BloodCenterRepository;
import com.example.blooddonationsystem.service.BloodCenterService;
import com.example.blooddonationsystem.service.UserService;
import com.example.blooddonationsystem.validation.BloodCenterValidation;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.DayOfWeek;
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

    @Override
    public BloodCenter add(BloodCenterDTO newCenter) {
        if (BloodCenterValidation.isNewCenterInvalid(newCenter)) {
            throw new InvalidDataException();
        }
        BloodCenter center = modelMapper.map(newCenter, BloodCenter.class);
        User manager = userService.register(newCenter.getManager());
        if (manager == null) {
            throw new UserNotFoundException();
        }
        center.setManager(manager);
        return bloodCenterRepository.save(center);
    }

    @Override
    public BloodCenter changeImage(Long centerId, MultipartFile image) {
        if (BloodCenterValidation.isChangeImageInvalid(centerId, image)) {
            throw new InvalidDataException();
        }
        BloodCenter center = bloodCenterRepository.findById(centerId).orElse(null);
        if (center == null) {
            throw new BloodCenterNotFoundException();
        }
        Image centerImage;
        try {
            centerImage = new Image(image.getOriginalFilename(), image.getContentType(), image.getBytes());
        } catch (IOException e) {
            throw new ChangeImageException();
        }
        center.setImage(centerImage);
        return bloodCenterRepository.save(center);
    }

    @Override
    public BloodCenter getManagerBloodCenter(String managerUsername) {
        if (BloodCenterValidation.isManagerBloodCenterInvalid(managerUsername)) {
            throw new InvalidDataException();
        }
        User manager = userService.findByUsername(managerUsername);
        if (manager == null) {
            throw new UserNotFoundException();
        }
        return bloodCenterRepository.findByManager(manager);
    }

    @Override
    public BloodCenter edit(EditBloodCenterDTO editBloodCenterDTO) {
        if (BloodCenterValidation.isEditCenterInvalid(editBloodCenterDTO)) {
            throw new InvalidDataException();
        }
        BloodCenter center = bloodCenterRepository.findById(editBloodCenterDTO.getId()).orElse(null);
        if (center == null) {
            throw new BloodCenterNotFoundException();
        }
        center.setName(editBloodCenterDTO.getName());
        center.setEmail(editBloodCenterDTO.getEmail());
        center.setAddress(editBloodCenterDTO.getAddress());
        center.setCity(editBloodCenterDTO.getCity());
        center.setCountry(editBloodCenterDTO.getCountry());
        center.setPhoneNumber(editBloodCenterDTO.getPhoneNumber());
        center.setDescription(editBloodCenterDTO.getDescription());
        center.setWorkingTimeFrom(editBloodCenterDTO.getWorkingTimeFrom());
        center.setWorkingTimeTo(editBloodCenterDTO.getWorkingTimeTo());
        center.setCapacity(editBloodCenterDTO.getCapacity());
        return bloodCenterRepository.save(center);
    }

    @Override
    public List<BloodCenter> getAll() {
        return bloodCenterRepository.findAll();
    }

    @Override
    public BloodCenter getById(Long id) {
        if (BloodCenterValidation.isGetByIdInvalid(id)) {
            throw new InvalidDataException();
        }
        BloodCenter center = bloodCenterRepository.findById(id).orElse(null);
        if (center == null) {
            throw new BloodCenterNotFoundException();
        }
        return center;
    }

    @Override
    public List<BloodCenter> searchAndSortFreeCenters(String sortBy, String sortDirection, LocalDateTime dateTime, String center, String address) {
        if (BloodCenterValidation.isSearchAndSortFreeBloodCentersInvalid(sortBy, sortDirection, dateTime)) {
            throw new InvalidDataException();
        }
        List<BloodCenter> centers = searchAndSort(center, address, sortBy, sortDirection);
        if (centers == null) {
            throw new InvalidDataException();
        }
        if (dateTime == null) {
            return centers;
        }
        if (dateTime != null && dateTime.compareTo(LocalDateTime.now()) < 0) {
            return new ArrayList<>();
        }
        if (dateTime.getDayOfWeek().equals(DayOfWeek.SATURDAY) || dateTime.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            return new ArrayList<>();
        }
        List<BloodCenter> freeBloodCenters = new ArrayList<>();
        for (BloodCenter bloodCenter : centers) {
            if (isDuringWorkingHours(dateTime, bloodCenter)) {

                int overlappingAppointments = countOverlappingAppointments(bloodCenter.getAppointments(), dateTime);
                if (!isCapacityExceeded(bloodCenter.getCapacity(), overlappingAppointments)) {
                    freeBloodCenters.add(bloodCenter);
                }
            }

        }
        return freeBloodCenters;
    }

    private boolean isDuringWorkingHours(LocalDateTime checking, BloodCenter center) {
        return (checking.getHour() >= center.getWorkingTimeFrom() && checking.plusMinutes(30).getHour() < center.getWorkingTimeTo());
    }

    private List<BloodCenter> searchAndSort(String center, String address, String sortBy, String sortDirection) {

        if (sortBy.equals("center") && sortDirection.equals("ascending")) {
            return bloodCenterRepository.searchBloodCenters(center, address, Sort.by(Sort.Direction.ASC, "name"));
        }
        if (sortBy.equals("center") && sortDirection.equals("descending")) {
            return bloodCenterRepository.searchBloodCenters(center, address, Sort.by(Sort.Direction.DESC, "name"));
        }
        if (sortBy.equals("address") && sortDirection.equals("ascending")) {
            return bloodCenterRepository.searchBloodCenters(center, address, Sort.by(Sort.Direction.ASC, "address")
                    .and(Sort.by(Sort.Direction.ASC, "city")
                            .and(Sort.by(Sort.Direction.ASC, "country"))));
        }
        if (sortBy.equals("address") && sortDirection.equals("descending")) {
            return bloodCenterRepository.searchBloodCenters(center, address, Sort.by(Sort.Direction.DESC, "address")
                    .and(Sort.by(Sort.Direction.DESC, "city")
                            .and(Sort.by(Sort.Direction.DESC, "country"))));
        }
        return null;
    }

    private Boolean isCapacityExceeded(int allowedCapacity, int overlappingAppointments) {
        return overlappingAppointments >= allowedCapacity;
    }

    private int countOverlappingAppointments(Set<Appointment> existingAppointments, LocalDateTime newAppointmentTime) {
        int overlappingAppointments = 0;
        for (Appointment existingAppointment : existingAppointments) {
            if (isOverlapping(existingAppointment.getStartDateTime(), newAppointmentTime)) {
                overlappingAppointments++;
            }
        }
        return overlappingAppointments;
    }

    private Boolean isOverlapping(LocalDateTime existingAppointment, LocalDateTime newAppointment) {
        return (newAppointment.isBefore(existingAppointment.plusMinutes(30)) &&
                existingAppointment.isBefore(newAppointment.plusMinutes(30)));
    }

}
