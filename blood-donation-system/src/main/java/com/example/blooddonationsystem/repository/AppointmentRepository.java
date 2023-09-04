package com.example.blooddonationsystem.repository;

import com.example.blooddonationsystem.model.Appointment;
import com.example.blooddonationsystem.model.BloodCenter;
import com.example.blooddonationsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    public List<Appointment> findByDonor(User donor);
    public List<Appointment> findByCenter(BloodCenter centre);
    @Query("select a from Appointment a where a.donor = :donor AND a.startDateTime > :now")
    public List<Appointment> getNotPassedAppointments(User donor, LocalDateTime now);

    @Query("select a from Appointment a where a.donor = :donor AND a.startDateTime <= :now")
    public List<Appointment> getPassedAppointments(User donor, LocalDateTime now);
}
