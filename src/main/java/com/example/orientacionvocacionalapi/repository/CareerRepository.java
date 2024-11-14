package com.example.orientacionvocacionalapi.repository;


import com.example.orientacionvocacionalapi.model.entity.Career;
import com.example.orientacionvocacionalapi.model.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CareerRepository extends JpaRepository<Career, Long> {
    List<Career> findByLocation(Location location);
}