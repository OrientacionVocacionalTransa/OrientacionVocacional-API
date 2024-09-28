package com.example.orientacionvocacionalapi.service.impl;

import com.example.orientacionvocacionalapi.model.entity.Carrera;
import com.example.orientacionvocacionalapi.model.entity.Ubicacion;
import com.example.orientacionvocacionalapi.repository.CarreraRepository;
import com.example.orientacionvocacionalapi.repository.UbicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CarreraService {

    @Autowired
    private CarreraRepository carreraRepository;

    @Autowired
    private UbicacionRepository ubicacionRepository;

    public List<Carrera> obtenerCarrerasPorUbicacion(String ciudad, String region, String pais) {
        Ubicacion ubicacion = ubicacionRepository.findByCiudadAndRegionAndPais(ciudad, region, pais);
        if (ubicacion != null) {
            return carreraRepository.findByUbicacion(ubicacion);
        }
        return null;
    }

    public Carrera getCarreraById(Long id) throws Exception {
        return carreraRepository.findById(id)
                .orElseThrow(() -> new Exception("Carrera no encontrada con id: " + id));
    }
}
