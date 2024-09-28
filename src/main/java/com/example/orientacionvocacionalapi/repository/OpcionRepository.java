package com.example.orientacionvocacionalapi.repository;
import com.example.orientacionvocacionalapi.model.entity.Opcion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpcionRepository extends JpaRepository<Opcion, Long> {
}
