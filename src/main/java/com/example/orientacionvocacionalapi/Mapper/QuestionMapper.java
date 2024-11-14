package com.example.orientacionvocacionalapi.Mapper;

import com.example.orientacionvocacionalapi.dto.QuestionDTO;
import com.example.orientacionvocacionalapi.model.entity.Question;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class QuestionMapper {
    private final ModelMapper modelMapper;

    public QuestionMapper(ModelMapper modelMapper){
        this.modelMapper=modelMapper;
    }

    public QuestionDTO toDTO(Question question){
        QuestionDTO questionDTO = modelMapper.map(question, QuestionDTO.class);

        return questionDTO;
    }

    public Question toEntity(QuestionDTO questionDTO){
        return modelMapper.map(questionDTO, Question.class);
    }
}