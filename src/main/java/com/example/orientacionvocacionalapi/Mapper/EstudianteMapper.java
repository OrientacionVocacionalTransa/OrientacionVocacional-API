package com.example.orientacionvocacionalapi.Mapper;

import com.example.orientacionvocacionalapi.dto.EstudianteDTO;
import com.example.orientacionvocacionalapi.model.entity.Estudiante;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EstudianteMapper {

    private final ModelMapper modelMapper;

    public EstudianteMapper(ModelMapper modelMapper){
        this.modelMapper=modelMapper;
    }

    public EstudianteDTO toDTO(Estudiante estudiante){
        return modelMapper.map(estudiante, EstudianteDTO.class);
    }

    public Estudiante toEntity(EstudianteDTO estudianteDTO){
        return modelMapper.map(estudianteDTO, Estudiante.class);
    }
}
