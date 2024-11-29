package com.example.orientacionvocacionalapi.service.impl;

import com.example.orientacionvocacionalapi.Mapper.AvailabilityMapper;
import com.example.orientacionvocacionalapi.dto.AvailabilityDTO;
import com.example.orientacionvocacionalapi.exception.ResourceNotFoundException;
import com.example.orientacionvocacionalapi.model.entity.Adviser;
import com.example.orientacionvocacionalapi.model.entity.Availability;
import com.example.orientacionvocacionalapi.repository.AdviserRepository;
import com.example.orientacionvocacionalapi.repository.AvailabilityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AvailabilityService {

    private final AvailabilityRepository availabilityRepository;
    private final AdviserRepository adviserRepository;
    private final AvailabilityMapper availabilityMapper;

    public List<AvailabilityDTO> getAvailabilities(Long adviserId) {
        List<Availability> availabilities = availabilityRepository.findByAdviserId(Integer.valueOf(Math.toIntExact(adviserId)));
        return availabilities.stream()
                .map(availabilityMapper::mapToDTO)
                .toList();
    }

    public void updateAvailabilities(Long adviserId, List<AvailabilityDTO> availabilityDTOs) {
        Adviser adviser = adviserRepository.findById(adviserId)
                .orElseThrow(() -> new ResourceNotFoundException("Adviser not found with ID: " + adviserId));


        List<Availability> existingAvailabilities = availabilityRepository.findByAdviserId(Integer.valueOf(Math.toIntExact(adviserId)));


        for (AvailabilityDTO dto : availabilityDTOs) {
            Optional<Availability> existingAvailabilityOpt = existingAvailabilities.stream()
                    .filter(avail -> avail.getDayOfWeek().equals(dto.getDayOfWeek()))
                    .findFirst();

            if (existingAvailabilityOpt.isPresent()) {

                Availability existingAvailability = existingAvailabilityOpt.get();
                existingAvailability.setStartTime(dto.getStartTime());
                existingAvailability.setEndTime(dto.getEndTime());

            } else {
                // Si no existe, creamos uno nuevo
                Availability newAvailability = availabilityMapper.mapToEntity(dto, adviser);
                availabilityRepository.save(newAvailability);
            }
        }

        availabilityRepository.saveAll(existingAvailabilities);
    }

    public void deleteAvailability(Long adviserId, String dayOfWeek) {
        Adviser adviser = adviserRepository.findById(adviserId)
                .orElseThrow(() -> new ResourceNotFoundException("Adviser not found with ID: " + adviserId));

        Availability availability = availabilityRepository.findByAdviserIdAndDayOfWeek(Math.toIntExact(adviserId), dayOfWeek)
                .orElseThrow(() -> new ResourceNotFoundException("Availability not found for adviser and day"));

        availabilityRepository.delete(availability);
    }

    public Availability addAvailability(Long adviserId, AvailabilityDTO availabilityDto) {
        Adviser adviser = adviserRepository.findById(adviserId)
                .orElseThrow(() -> new ResourceNotFoundException("Adviser not found with ID: " + adviserId));


        boolean exists = availabilityRepository.existsByAdviserIdAndDayOfWeek(Integer.valueOf(Math.toIntExact(adviserId)), availabilityDto.getDayOfWeek());
        if (exists) {
            throw new IllegalArgumentException("Ya existe disponibilidad para el día indicado.");
        }


        if (availabilityDto.getStartTime().isAfter(availabilityDto.getEndTime())) {
            throw new IllegalArgumentException("La hora de inicio debe ser anterior a la hora de finalización.");
        }

        Availability availability = new Availability();
        availability.setAdviser(adviser);
        availability.setDayOfWeek(availabilityDto.getDayOfWeek());
        availability.setStartTime(availabilityDto.getStartTime());
        availability.setEndTime(availabilityDto.getEndTime());

        return availabilityRepository.save(availability);
    }

}