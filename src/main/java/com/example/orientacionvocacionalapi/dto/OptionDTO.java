package com.example.orientacionvocacionalapi.dto;

import lombok.Data;

@Data
public class OptionDTO {
    private Long id;
    private String text;
    private Integer score;

    // Constructor
    public OptionDTO() {
    }

}