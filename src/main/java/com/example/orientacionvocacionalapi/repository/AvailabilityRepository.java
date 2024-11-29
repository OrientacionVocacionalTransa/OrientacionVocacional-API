package com.example.orientacionvocacionalapi.repository;

import com.example.orientacionvocacionalapi.model.entity.Availability;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Long> {

    List<Availability> findByAdviserId(Integer adviserId);
    @Transactional
    void deleteByAdviserId(Integer adviserId);


    Optional<Availability> findByAdviserIdAndDayOfWeek(int adviserId, String dayOfWeek);
    boolean existsByAdviserIdAndDayOfWeek(Integer adviserId, String dayOfWeek);


}