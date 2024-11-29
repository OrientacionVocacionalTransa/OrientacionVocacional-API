package com.example.orientacionvocacionalapi.service.impl;

import com.example.orientacionvocacionalapi.Mapper.AdminMapper;
import com.example.orientacionvocacionalapi.dto.AdminDTO;
import com.example.orientacionvocacionalapi.model.entity.Admin;
import com.example.orientacionvocacionalapi.model.enums.ERole;
import com.example.orientacionvocacionalapi.repository.AdminRepository;
import com.example.orientacionvocacionalapi.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AdminService {


    private final AdminMapper adminMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AdminRepository adminRepository;

    public AdminService(AdminMapper adminMapper, PasswordEncoder passwordEncoder, UserRepository userRepository, AdminRepository adminRepository) {
        this.adminMapper = adminMapper;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
    }

    public AdminDTO registerAdvisor(AdminDTO adminDTO) {
        Integer randomId= generateUniqueRandomId();

        Admin admin = adminMapper.toEntity(adminDTO);
        ERole eRole = ERole.ADMIN;
        admin.setId(randomId);
        admin.setImg_profile("profile.png");
        admin.setFirstName(adminDTO.getFirstName());
        admin.setLastName(adminDTO.getLastName());
        admin.setEmail(adminDTO.getEmail());
        admin.setPassword(passwordEncoder.encode(adminDTO.getPassword()));
        admin.setRole(eRole);
        admin = userRepository.save(admin);
        return adminMapper.toDTO(admin);
    }


    private Integer generateUniqueRandomId() {
        Random random = new Random();
        Integer randomId;

        do {
            randomId = 1000000 + random.nextInt(9000000);
        } while (
                adminRepository.existsById(Long.valueOf(randomId)));


        return randomId;
    }
}
