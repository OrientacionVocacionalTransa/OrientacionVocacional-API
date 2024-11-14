package com.example.orientacionvocacionalapi.repository;
import com.example.orientacionvocacionalapi.model.entity.Adviser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdviserRepository extends JpaRepository<Adviser, Long> {
    Optional<Adviser> findByFirstNameAndLastName(String firstName, String lastName);

    Optional<Adviser> findByEmail(String email);
}