package com.example.orientacionvocacionalapi.Mapper;

import com.example.orientacionvocacionalapi.dto.StudentDTO;
import com.example.orientacionvocacionalapi.model.entity.Student;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    private final ModelMapper modelMapper;

    public StudentMapper(ModelMapper modelMapper){
        this.modelMapper=modelMapper;
    }

    public StudentDTO toDTO(Student student){
        return modelMapper.map(student, StudentDTO.class);
    }

    public Student toEntity(StudentDTO studentDTO){
        return modelMapper.map(studentDTO, Student.class);
    }
}
