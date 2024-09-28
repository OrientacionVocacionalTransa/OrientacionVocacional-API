package com.example.orientacionvocacionalapi.Controller;

import com.example.orientacionvocacionalapi.model.entity.Carrera;
import com.example.orientacionvocacionalapi.model.entity.Ubicacion;
import com.example.orientacionvocacionalapi.repository.CarreraRepository;
import com.example.orientacionvocacionalapi.repository.UbicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carreras")
public class CarreraController {

    @Autowired
    private CarreraRepository carreraRepository;

    @Autowired
    private UbicacionRepository ubicacionRepository;


    @PostMapping("/insertarubi")
    public ResponseEntity<Ubicacion> agregarUbicacion(@RequestBody Ubicacion ubicacion) {
        Ubicacion nuevaUbicacion = ubicacionRepository.save(ubicacion);
        return ResponseEntity.ok(nuevaUbicacion);
    }

    @PostMapping("/insertarcarrera")
    public ResponseEntity<Carrera> agregarCarrera(@RequestParam String nombreCarrera, @RequestParam Long ubicacionId) {
        Ubicacion ubicacion = ubicacionRepository.findById(ubicacionId).orElse(null);
        if (ubicacion == null) {
            return ResponseEntity.badRequest().body(null);
        }

        Carrera carrera = new Carrera();
        carrera.setNombre(nombreCarrera);
        carrera.setUbicacion(ubicacion);

        Carrera nuevaCarrera = carreraRepository.save(carrera);
        return ResponseEntity.ok(nuevaCarrera);
    }

    @GetMapping("/porubicacion/{ubicacionId}")
    public ResponseEntity<List<Carrera>> obtenerCarrerasPorUbicacion(@PathVariable Long ubicacionId) {
        Ubicacion ubicacion = ubicacionRepository.findById(ubicacionId).orElse(null);
        if (ubicacion == null) {
            return ResponseEntity.badRequest().body(null); // Si no se encuentra la ubicación
        }

        List<Carrera> carreras = carreraRepository.findByUbicacion(ubicacion);
        return ResponseEntity.ok(carreras);
    }
}
