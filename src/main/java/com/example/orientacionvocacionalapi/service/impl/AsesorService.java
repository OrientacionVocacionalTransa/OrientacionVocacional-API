package com.example.orientacionvocacionalapi.service.impl;
import com.example.orientacionvocacionalapi.model.entity.Asesor;
import com.example.orientacionvocacionalapi.repository.AsesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AsesorService {
    @Autowired
    private AsesorRepository asesorRepository;



    public Optional<Asesor> obtenerPerfilAsesorPorEmail(String email) {
        return asesorRepository.findByEmail(email);
    }


    public List<Asesor> listarTodosLosAsesores() {
        return asesorRepository.findAll();
    }

}