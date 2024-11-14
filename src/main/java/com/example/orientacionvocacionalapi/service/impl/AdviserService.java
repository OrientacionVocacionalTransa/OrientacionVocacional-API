package com.example.orientacionvocacionalapi.service.impl;
import com.example.orientacionvocacionalapi.Mapper.AdviserMapper;
import com.example.orientacionvocacionalapi.dto.AdviserDTO;
import com.example.orientacionvocacionalapi.exception.BadRequestException;
import com.example.orientacionvocacionalapi.model.entity.Adviser;
import com.example.orientacionvocacionalapi.model.enums.ERole;
import com.example.orientacionvocacionalapi.repository.AdviserRepository;
import com.example.orientacionvocacionalapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class AdviserService {
    @Autowired
    private AdviserRepository adviserRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AdviserMapper adviserMapper;

    public Optional<Adviser> getAdvisorProfileByEmail(String email) {
        return adviserRepository.findByEmail(email);
    }


    public List<AdviserDTO> listAllAdvisors() {
        List<Adviser> advisors = adviserRepository.findAll();
        return advisors.stream()
                .map(adviserMapper::toDTO)
                .toList();
    }

    public AdviserDTO registerAdvisor(AdviserDTO adviserDTO) {
        Integer randomId= generateUniqueRandomId();
        adviserRepository.findByFirstNameAndLastName(adviserDTO.getFirstName(), adviserDTO.getLastName())
                .ifPresent(existingAdviser -> {
                    throw new BadRequestException("El asesor ya existe con el mismo nombre y apellido");
                });
        Adviser asesor = adviserMapper.toEntity(adviserDTO);
        ERole eRole = ERole.ADVISER;
        asesor.setId(randomId);
        asesor.setImg_profile("profile.png");
        asesor.setFirstName(adviserDTO.getFirstName());
        asesor.setLastName(adviserDTO.getLastName());
        asesor.setEmail(adviserDTO.getEmail());
        asesor.setSpecialty(adviserDTO.getSpecialty());
        asesor.setPassword(passwordEncoder.encode(adviserDTO.getPassword())); // Asegúrate de que estés utilizando un codificador de contraseñas
        asesor.setRole(eRole);
        asesor = userRepository.save(asesor);
        return adviserMapper.toDTO(asesor);
    }


    private Integer generateUniqueRandomId() {
        Random random = new Random();
        Integer randomId;

        do {
            randomId = 1000000 + random.nextInt(9000000);
        } while (adviserRepository.existsById(Long.valueOf(randomId)));

        return randomId;
    }

}