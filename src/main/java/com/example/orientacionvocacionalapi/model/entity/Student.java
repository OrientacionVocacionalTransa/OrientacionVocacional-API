package com.example.orientacionvocacionalapi.model.entity;
import com.example.orientacionvocacionalapi.model.enums.Plan;
import jakarta.persistence.Entity;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "student")
@Data
public class Student extends User{
    private String verificationCode;
    private boolean verified = false;
    @Enumerated(EnumType.STRING)
    private Plan plan;
}