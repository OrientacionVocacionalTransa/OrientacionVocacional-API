package com.example.orientacionvocacionalapi.repository;


import com.example.orientacionvocacionalapi.model.entity.Carrera;
import com.example.orientacionvocacionalapi.model.entity.Ubicacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarreraRepository extends JpaRepository<Carrera , Long> {
    List<Carrera> findByUbicacion(Ubicacion ubicacion);
}