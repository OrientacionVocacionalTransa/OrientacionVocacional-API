package com.example.orientacionvocacionalapi.model.entity;
import jakarta.persistence.Entity;

import jakarta.persistence.Table;

@Entity
@Table(name = "Estudiantes")
public class Estudiante extends User{

}