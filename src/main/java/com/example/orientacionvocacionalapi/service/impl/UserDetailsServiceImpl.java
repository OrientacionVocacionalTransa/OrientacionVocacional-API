package com.example.orientacionvocacionalapi.service.impl;

import com.example.orientacionvocacionalapi.model.entity.User;
import com.example.orientacionvocacionalapi.model.enums.ERole;
import com.example.orientacionvocacionalapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = usuarioRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + email);
        }

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRole())
        );
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(ERole role) {
        return role == null ? null :
                java.util.Collections.singletonList(new SimpleGrantedAuthority(role.name()));
    }
}
