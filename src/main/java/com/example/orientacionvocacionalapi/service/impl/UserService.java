package com.example.orientacionvocacionalapi.service.impl;

import com.example.orientacionvocacionalapi.dto.UserDTO;
import com.example.orientacionvocacionalapi.model.entity.User;
import com.example.orientacionvocacionalapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registrarUsuario(UserDTO usuarioDTO) {

        User user = new User();
        user.setFirstName(usuarioDTO.getFirstName());
        user.setLastName(usuarioDTO.getLastName());
        user.setEmail(usuarioDTO.getEmail());
        user.setPassword(usuarioDTO.getPassword());


        user.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));


        usuarioRepository.save(user);
    }
    public boolean login(String email, String password) {
        User usuario = usuarioRepository.findByEmail(email);
        if (usuario != null) {
            return passwordEncoder.matches(password, usuario.getPassword());  // Compara contraseñas
        }
        return false;
    }
}
