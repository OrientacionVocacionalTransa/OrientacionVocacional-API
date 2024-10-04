package com.example.orientacionvocacionalapi.service.impl;

import com.example.orientacionvocacionalapi.Mapper.AsesorMapper;
import com.example.orientacionvocacionalapi.Mapper.EstudianteMapper;
import com.example.orientacionvocacionalapi.dto.EstudianteDTO;
import com.example.orientacionvocacionalapi.exception.BadRequestException;
import com.example.orientacionvocacionalapi.exception.ResourceNotFoundException;
import com.example.orientacionvocacionalapi.model.entity.Estudiante;
import com.example.orientacionvocacionalapi.model.enums.ERole;
import com.example.orientacionvocacionalapi.repository.EstudianteRepository;
import com.example.orientacionvocacionalapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteService {
    @Autowired
    private UserRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private EstudianteMapper estudianteMapper;


    public List<EstudianteDTO> listarTodosLosEstudiantes() {
        List<Estudiante> estudiantes = estudianteRepository.findAll();
        return estudiantes.stream()
                .map(estudianteMapper::toDTO)
                .toList();
    }


    public Optional<Estudiante> obtenerPerfilEstudiantePorEmail(String email) {
        return estudianteRepository.findByEmail(email);
    }

    public EstudianteDTO findById(Long id){
        Estudiante estudiante = estudianteRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("El estudiante con ID " + id+ "no fue encontrado"));
        return estudianteMapper.toDTO(estudiante);
    }

    public EstudianteDTO registrarEstudiante(EstudianteDTO estudianteDTO) {
        estudianteRepository.findByFirstNameAndLastName(estudianteDTO.getFirstName(), estudianteDTO.getLastName())
                .ifPresent(existingAsesor -> {
                    throw new BadRequestException("El estudiante ya existe con el mismo nombre y apellido");
                });
        Estudiante estudiante = estudianteMapper.toEntity(estudianteDTO);
        ERole eRole = ERole.ESTUDIANTE;


        estudiante.setFirstName(estudianteDTO.getFirstName());
        estudiante.setLastName(estudianteDTO.getLastName());
        estudiante.setEmail(estudianteDTO.getEmail());
        estudiante.setPassword(passwordEncoder.encode(estudianteDTO.getPassword()));
        estudiante.setRole(eRole);
        estudiante = usuarioRepository.save(estudiante);
        return estudianteMapper.toDTO(estudiante);
    }
}
