package com.example.orientacionvocacionalapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class VocationalTestDTO {
    private List<QuestionDTO> questions;

}