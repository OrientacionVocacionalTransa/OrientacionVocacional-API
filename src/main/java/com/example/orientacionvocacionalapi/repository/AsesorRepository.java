package com.example.orientacionvocacionalapi.repository;
import com.example.orientacionvocacionalapi.model.entity.Asesor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AsesorRepository extends JpaRepository<Asesor, Long> {
    Optional<Asesor> findByFirstNameAndLastName(String firstName, String lastName);

    Optional<Asesor> findByEmail(String email);
}