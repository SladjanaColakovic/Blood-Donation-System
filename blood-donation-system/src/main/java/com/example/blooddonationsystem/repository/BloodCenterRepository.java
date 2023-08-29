package com.example.blooddonationsystem.repository;

import com.example.blooddonationsystem.model.BloodCenter;
import com.example.blooddonationsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloodCenterRepository extends JpaRepository<BloodCenter, Long> {
    public BloodCenter findByManager(User manager);
}
