package com.example.orientacionvocacionalapi.repository;
import com.example.orientacionvocacionalapi.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);
    Optional<Student> findById(Long id);
    Optional<Student> findByFirstNameAndLastName(String firstName, String lastName);
}
