package com.example.orientacionvocacionalapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AdviserDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String specialty;
}