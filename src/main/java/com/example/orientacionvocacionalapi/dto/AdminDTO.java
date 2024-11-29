package com.example.orientacionvocacionalapi.dto;

import lombok.Data;

@Data
public class AdminDTO {
    private Integer id;
    private String img_profile;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
