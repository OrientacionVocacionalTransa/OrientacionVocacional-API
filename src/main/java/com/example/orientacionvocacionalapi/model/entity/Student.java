package com.example.orientacionvocacionalapi.model.entity;
import jakarta.persistence.Entity;

import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "student")
@Data
public class Student extends User{

}