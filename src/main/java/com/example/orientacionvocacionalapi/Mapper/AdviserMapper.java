package com.example.orientacionvocacionalapi.Mapper;

import com.example.orientacionvocacionalapi.dto.AdviserDTO;
import com.example.orientacionvocacionalapi.model.entity.Adviser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AdviserMapper {

    private final ModelMapper modelMapper;

    public AdviserMapper(ModelMapper modelMapper){
        this.modelMapper=modelMapper;
    }

    public AdviserDTO toDTO(Adviser adviser){
        return modelMapper.map(adviser, AdviserDTO.class);
    }

    public Adviser toEntity(AdviserDTO adviserDTO){
        return modelMapper.map(adviserDTO, Adviser.class);
    }
}

