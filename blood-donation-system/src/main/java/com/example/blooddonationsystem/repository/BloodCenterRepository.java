package com.example.blooddonationsystem.repository;

import com.example.blooddonationsystem.model.BloodCenter;
import com.example.blooddonationsystem.model.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BloodCenterRepository extends JpaRepository<BloodCenter, Long> {
    public BloodCenter findByManager(User manager);
    @Query("select c from BloodCenter c where (:center is null OR lower(c.name) like concat('%', lower(:center), '%') ) AND" +
            "(:address is null OR concat(lower(c.address), ', ', lower(c.city), ', ', lower(c.country) ) like concat('%', lower(:address), '%' ) )")
    public List<BloodCenter> searchBloodCenters(String center, String address, Sort sort);


}
