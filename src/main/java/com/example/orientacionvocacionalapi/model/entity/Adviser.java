package com.example.orientacionvocacionalapi.model.entity;
import jakarta.persistence.Entity;

import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Entity
@Table(name = "adviser")
@Data
public class Adviser extends User{
    @NotBlank(message = "La especialidad es obligatoria")
    private String specialty;
}