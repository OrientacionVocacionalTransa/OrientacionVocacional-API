package com.example.orientacionvocacionalapi.Mapper;

import com.example.orientacionvocacionalapi.dto.AdminDTO;
import com.example.orientacionvocacionalapi.model.entity.Admin;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper {
    private final ModelMapper modelMapper;

    public AdminMapper(ModelMapper modelMapper){
        this.modelMapper=modelMapper;
    }

    public AdminDTO toDTO(Admin admin){
        return modelMapper.map(admin, AdminDTO.class);
    }

    public Admin toEntity(AdminDTO adminDTO){
        return modelMapper.map(adminDTO, Admin.class);
    }
}
