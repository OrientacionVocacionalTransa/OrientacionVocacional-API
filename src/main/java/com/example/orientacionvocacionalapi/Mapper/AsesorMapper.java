package com.example.orientacionvocacionalapi.Mapper;

import com.example.orientacionvocacionalapi.dto.AsesorDTO;
import com.example.orientacionvocacionalapi.model.entity.Asesor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AsesorMapper {

    private final ModelMapper modelMapper;

    public AsesorMapper(ModelMapper modelMapper){
        this.modelMapper=modelMapper;
    }

    public AsesorDTO toDTO(Asesor asesor){
        return modelMapper.map(asesor, AsesorDTO.class);
    }

    public Asesor toEntity(AsesorDTO asesorDTO){
        return modelMapper.map(asesorDTO, Asesor.class);
    }
}

