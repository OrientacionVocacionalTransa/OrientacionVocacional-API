package com.example.orientacionvocacionalapi.service.impl;

import com.example.orientacionvocacionalapi.model.entity.Estudiante;
import com.example.orientacionvocacionalapi.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EstudianteService {
    @Autowired
    private EstudianteRepository estudianteRepository;
    public List<Estudiante> listarTodosLosEstudiantes() {
        return estudianteRepository.findAll();
    }
}
