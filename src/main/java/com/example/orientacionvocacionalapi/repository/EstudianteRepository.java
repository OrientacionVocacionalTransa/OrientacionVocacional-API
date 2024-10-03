package com.example.orientacionvocacionalapi.repository;
import com.example.orientacionvocacionalapi.model.entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
}
