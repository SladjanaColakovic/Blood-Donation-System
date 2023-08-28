package com.example.blooddonationsystem.repository;

import com.example.blooddonationsystem.model.Appointment;
import com.example.blooddonationsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    public List<Appointment> findByDonor(User donor);
}
