package com.example.blooddonationsystem.service.implementation;

import com.example.blooddonationsystem.dto.AppointmentDTO;
import com.example.blooddonationsystem.dto.DonorAppointmentResponseDTO;
import com.example.blooddonationsystem.dto.ManagerAppointmentResponseDTO;
import com.example.blooddonationsystem.model.Appointment;
import com.example.blooddonationsystem.model.BloodCenter;
import com.example.blooddonationsystem.model.User;
import com.example.blooddonationsystem.repository.AppointmentRepository;
import com.example.blooddonationsystem.service.AppointmentService;
import com.example.blooddonationsystem.service.BloodCenterService;
import com.example.blooddonationsystem.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class AppointmentServiceImplementation implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private BloodCenterService bloodCenterService;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Appointment schedule(AppointmentDTO newAppointment) {

        BloodCenter center = bloodCenterService.getById(newAppointment.getCenterId());
        User donor = userService.findByUsername(newAppointment.getDonorUsername());
        Set<Appointment> existingAppointments = center.getAppointments();
        int overlappingAppointments = countOverlappingAppointments(existingAppointments, newAppointment);
        if (overlappingAppointments >= center.getCapacity()) {
            return null;
        }
        Appointment appointment = new Appointment();
        appointment.setCenter(center);
        appointment.setDonor(donor);
        appointment.setStartDateTime(newAppointment.getStartDateTime());
        appointment.setCanceled(false);
        return appointmentRepository.save(appointment);
    }

    @Override
    public List<DonorAppointmentResponseDTO> getDonorAppointments(String donorUsername) {
        User donor = userService.findByUsername(donorUsername);
        List<DonorAppointmentResponseDTO> appointments = appointmentRepository
                .getPassedAppointments(donor, LocalDateTime.now()).stream()
                .map(appointment -> modelMapper.map(appointment, DonorAppointmentResponseDTO.class))
                .toList();
        return appointments;
    }

    @Override
    public List<ManagerAppointmentResponseDTO> getBloodCenterAppointments(String managerUsername) {
        BloodCenter center = bloodCenterService.getManagerBloodCenter(managerUsername);
        List<ManagerAppointmentResponseDTO> appointments = appointmentRepository
                .findByCenter(center)
                .stream()
                .map(appointment -> modelMapper.map(appointment, ManagerAppointmentResponseDTO.class))
                .toList();
        return appointments;
    }

    @Override
    public List<DonorAppointmentResponseDTO> getNotPassedAppointments(String donorUsername) {
        User donor = userService.findByUsername(donorUsername);
        return appointmentRepository
                .getNotPassedAppointments(donor, LocalDateTime.now())
                .stream()
                .map(appointment -> modelMapper.map(appointment, DonorAppointmentResponseDTO.class))
                .toList();
    }

    @Override
    public Boolean cancel(Long id) {
        Appointment appointment = appointmentRepository.findById(id).get();
        if (appointment.getStartDateTime().minusHours(24).compareTo(LocalDateTime.now()) >= 0) {
            appointmentRepository.deleteById(id);
            return true;
        }
        return false;

    }

    @Override
    public List<DonorAppointmentResponseDTO> sortDonorAppointments(String donorUsername, String sortBy, String sortDirection, String searchText, LocalDate searchDate) {
        User donor = userService.findByUsername(donorUsername);
        List<Appointment> passedAppointments = new ArrayList<>();


        if (sortBy.equals("center")) {
            if (sortDirection.equals("ascending")) {
                passedAppointments = appointmentRepository.getDonorAppointments(Sort.by(Sort.Direction.ASC, "center.name"), donor, LocalDateTime.now(), searchText, (searchDate != null)? searchDate.atStartOfDay(): null, (searchDate != null)? searchDate.plusDays(1).atStartOfDay(): null);
            }
            if (sortDirection.equals("descending")) {
                passedAppointments = appointmentRepository.getDonorAppointments(Sort.by(Sort.Direction.DESC, "center.name"), donor, LocalDateTime.now(), searchText,  (searchDate != null)? searchDate.atStartOfDay(): null, (searchDate != null)? searchDate.plusDays(1).atStartOfDay(): null);
            }
        }
        if (sortBy.equals("address")) {
            if (sortDirection.equals("ascending")) {
                passedAppointments = appointmentRepository
                        .getDonorAppointments(Sort.by(Sort.Direction.ASC, "center.address")
                                .and(Sort.by(Sort.Direction.ASC, "center.city")
                                        .and(Sort.by(Sort.Direction.ASC, "center.country"))), donor, LocalDateTime.now(), searchText,  (searchDate != null)? searchDate.atStartOfDay(): null, (searchDate != null)? searchDate.plusDays(1).atStartOfDay(): null);
            }
            if (sortDirection.equals("descending")) {
                passedAppointments = appointmentRepository
                        .getDonorAppointments(Sort.by(Sort.Direction.DESC, "center.address")
                                .and(Sort.by(Sort.Direction.DESC, "center.city")
                                        .and(Sort.by(Sort.Direction.DESC, "center.country"))), donor, LocalDateTime.now(), searchText,  (searchDate != null)? searchDate.atStartOfDay(): null, (searchDate != null)? searchDate.plusDays(1).atStartOfDay(): null);
            }
        }
        if (sortBy.equals("date")) {
            if (sortDirection.equals("ascending")) {
                passedAppointments = appointmentRepository.getDonorAppointments(Sort.by(Sort.Direction.ASC, "startDateTime"), donor, LocalDateTime.now(), searchText, (searchDate != null)? searchDate.atStartOfDay(): null, (searchDate != null)? searchDate.plusDays(1).atStartOfDay(): null);
            }
            if (sortDirection.equals("descending")) {
                passedAppointments = appointmentRepository.getDonorAppointments(Sort.by(Sort.Direction.DESC, "startDateTime"), donor, LocalDateTime.now(), searchText, (searchDate != null)? searchDate.atStartOfDay(): null, (searchDate != null)? searchDate.plusDays(1).atStartOfDay(): null);
            }
        }


        return passedAppointments
                .stream()
                .map(appointment -> modelMapper.map(appointment, DonorAppointmentResponseDTO.class))
                .toList();
    }

    private int countOverlappingAppointments(Set<Appointment> existingAppointments, AppointmentDTO newAppointment) {
        int overlappingAppointments = 0;
        for (Appointment existingAppointment : existingAppointments) {
            if (isOverlapping(existingAppointment.getStartDateTime(), newAppointment.getStartDateTime())) {
                overlappingAppointments++;
            }
        }
        return overlappingAppointments;
    }

    private Boolean isOverlapping(LocalDateTime existingAppointment, LocalDateTime newAppointmnet) {
        if ((newAppointmnet.isBefore(existingAppointment.plusMinutes(30))
                || newAppointmnet.isEqual(existingAppointment.plusMinutes(30))
        ) &&
                (existingAppointment.isBefore(newAppointmnet.plusMinutes(30))
                        || existingAppointment.isEqual(newAppointmnet.plusMinutes(30))
                )) {
            return true;
        }
        return false;
    }
}
