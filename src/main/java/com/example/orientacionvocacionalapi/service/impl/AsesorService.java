package com.example.orientacionvocacionalapi.service.impl;
import com.example.orientacionvocacionalapi.Mapper.AsesorMapper;
import com.example.orientacionvocacionalapi.dto.AsesorDTO;
import com.example.orientacionvocacionalapi.exception.BadRequestException;
import com.example.orientacionvocacionalapi.model.entity.Asesor;
import com.example.orientacionvocacionalapi.model.enums.ERole;
import com.example.orientacionvocacionalapi.repository.AsesorRepository;
import com.example.orientacionvocacionalapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AsesorService {
    @Autowired
    private AsesorRepository asesorRepository;

    @Autowired private UserRepository usuarioRepository;

    @Autowired private PasswordEncoder passwordEncoder;

    @Autowired
    private AsesorMapper asesorMapper;

    public Optional<Asesor> obtenerPerfilAsesorPorEmail(String email) {
        return asesorRepository.findByEmail(email);
    }


    public List<Asesor> listarTodosLosAsesores() {
        return asesorRepository.findAll();
    }

    public AsesorDTO registrarAsesor(AsesorDTO asesorDTO) {
        asesorRepository.findByFirstNameAndLastName(asesorDTO.getFirstName(), asesorDTO.getLastName())
                .ifPresent(existingAsesor -> {
                    throw new BadRequestException("El asesor ya existe con el mismo nombre y apellido");
                });
        Asesor asesor = asesorMapper.toEntity(asesorDTO);
        ERole eRole = ERole.ASESOR;

        asesor.setFirstName(asesorDTO.getFirstName());
        asesor.setLastName(asesorDTO.getLastName());
        asesor.setEmail(asesorDTO.getEmail());
        asesor.setEspecialidad(asesorDTO.getEspecialidad());
        asesor.setPassword(passwordEncoder.encode(asesorDTO.getPassword())); // Asegúrate de que estés utilizando un codificador de contraseñas
        asesor.setRole(eRole);
        asesor = usuarioRepository.save(asesor);
        return asesorMapper.toDTO(asesor);
    }

}