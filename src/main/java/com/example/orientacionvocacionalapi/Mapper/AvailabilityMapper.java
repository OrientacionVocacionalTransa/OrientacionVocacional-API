package com.example.orientacionvocacionalapi.Mapper;

import com.example.orientacionvocacionalapi.dto.AvailabilityDTO;
import com.example.orientacionvocacionalapi.model.entity.Adviser;
import com.example.orientacionvocacionalapi.model.entity.Availability;
import org.springframework.stereotype.Component;

@Component
public class AvailabilityMapper {

    public AvailabilityDTO mapToDTO(Availability availability) {
        return new AvailabilityDTO(
                availability.getDayOfWeek(),
                availability.getStartTime(),
                availability.getEndTime()
        );
    }

    public Availability mapToEntity(AvailabilityDTO dto, Adviser adviser) {
        Availability availability = new Availability();
        availability.setAdviser(adviser);
        availability.setDayOfWeek(dto.getDayOfWeek());
        availability.setStartTime(dto.getStartTime());
        availability.setEndTime(dto.getEndTime());
        return availability;
    }
}
