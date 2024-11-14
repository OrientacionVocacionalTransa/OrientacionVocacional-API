package com.example.orientacionvocacionalapi.Controller;

import com.example.orientacionvocacionalapi.model.entity.Career;
import com.example.orientacionvocacionalapi.model.entity.Location;
import com.example.orientacionvocacionalapi.repository.CareerRepository;
import com.example.orientacionvocacionalapi.repository.LocationRepository;
import com.example.orientacionvocacionalapi.service.impl.CareerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/career")
public class CareerController {

    @Autowired
    private CareerRepository careerRepository;

    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private CareerService careerService;


    @PostMapping("/addLocation")
    public ResponseEntity<Location> addLocation(@RequestBody Location location) {
        Location newLocation = locationRepository.save(location);
        return ResponseEntity.ok(newLocation);
    }

    @PostMapping("/addCareer")
    public ResponseEntity<Career> addCareer(@RequestParam String nameCareer, @RequestParam Long locationId, @RequestParam String img, @RequestParam String description, @RequestParam String price) {
        Location location = locationRepository.findById(locationId).orElse(null);
        if (location == null) {
            return ResponseEntity.badRequest().body(null);
        }

        Career career = new Career();
        career.setName(nameCareer);
        career.setLocation(location);


        Career newCareer = careerRepository.save(career);
        return ResponseEntity.ok(newCareer);
    }

    @GetMapping("/byLocation/{locationId}")
    public ResponseEntity<List<Career>> getCareersByLocationId(@PathVariable Long locationId) {
        Location location = locationRepository.findById(locationId).orElse(null);
        if (location == null) {
            return ResponseEntity.badRequest().body(null); // Si no se encuentra la ubicación
        }

        List<Career> careers = careerRepository.findByLocation(location);
        return ResponseEntity.ok(careers);
    }

    @GetMapping("/showCareers")
    public ResponseEntity<List<Career>> getCareers(){
        List<Career> careers = careerRepository.findAll();
        return ResponseEntity.ok(careers);
    }

    @GetMapping("/showLocations")
    public ResponseEntity<List<Location>> getLocations() {
        List<Location> locations = locationRepository.findAll();
        return ResponseEntity.ok(locations);
    }

    @GetMapping("/CareersByID/{careerId}")
    public ResponseEntity<?> getCareersByID(@PathVariable Long careerId) {
        try {
            Career career = careerService.getCareerById(careerId);
            return ResponseEntity.ok(career);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al obtener el usuario: " + e.getMessage());
        }
    }

    @GetMapping("/filterExactLocation")
    public ResponseEntity<List<Career>> filterCareersByLocation(
            @RequestParam String city,
            @RequestParam String region,
            @RequestParam String country
    ) {
        List<Career> careers = careerService.getCareersByLocation(city, region, country);
        if (careers != null && !careers.isEmpty()) {
            return ResponseEntity.ok(careers);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
