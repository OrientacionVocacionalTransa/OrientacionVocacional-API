package com.example.orientacionvocacionalapi.repository;


import com.example.orientacionvocacionalapi.model.entity.Ubicacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UbicacionRepository extends JpaRepository<Ubicacion , Long> {
    Ubicacion findByCiudadAndRegionAndPais(String ciudad, String region, String pais);
}