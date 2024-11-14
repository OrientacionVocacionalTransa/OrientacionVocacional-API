package com.example.orientacionvocacionalapi.repository;


import com.example.orientacionvocacionalapi.model.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
    Location findByCityAndRegionAndCountry(String ciudad, String region, String pais);
}