package com.example.orientacionvocacionalapi.service.impl;

import com.example.orientacionvocacionalapi.model.entity.Career;
import com.example.orientacionvocacionalapi.model.entity.Location;
import com.example.orientacionvocacionalapi.repository.CareerRepository;
import com.example.orientacionvocacionalapi.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CareerService {

    @Autowired
    private CareerRepository careerRepository;

    @Autowired
    private LocationRepository locationRepository;

    public List<Career> getCareersByLocation(String ciudad, String region, String pais) {
        Location location = locationRepository.findByCityAndRegionAndCountry(ciudad, region, pais);
        if (location != null) {
            return careerRepository.findByLocation(location);
        }
        return null;
    }

    public Career getCareerById(Long id) throws Exception {
        return careerRepository.findById(id)
                .orElseThrow(() -> new Exception("Carrera no encontrada con id: " + id));
    }
}
