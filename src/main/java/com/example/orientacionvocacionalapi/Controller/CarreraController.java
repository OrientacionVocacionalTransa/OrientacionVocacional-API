package com.example.orientacionvocacionalapi.Controller;

import com.example.orientacionvocacionalapi.model.entity.Carrera;
import com.example.orientacionvocacionalapi.model.entity.Ubicacion;
import com.example.orientacionvocacionalapi.repository.CarreraRepository;
import com.example.orientacionvocacionalapi.repository.UbicacionRepository;
import com.example.orientacionvocacionalapi.service.impl.CarreraService;
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
    @Autowired
    private CarreraService carreraService;


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

    @GetMapping("/mostrarcarreras")
    public ResponseEntity<List<Carrera>> obtenercarreras(){
        List<Carrera> carreras = carreraRepository.findAll();
        return ResponseEntity.ok(carreras);
    }

    @GetMapping("/mostrarubicaciones")
    public ResponseEntity<List<Ubicacion>> obtenerUbicaciones() {
        List<Ubicacion> ubicaciones = ubicacionRepository.findAll();
        return ResponseEntity.ok(ubicaciones);
    }

    @GetMapping("/carreraporID/{carreraId}")
    public ResponseEntity<?> obtenerCarrerasPorID(@PathVariable Long carreraId) {
        try {
            Carrera carrera = carreraService.getCarreraById(carreraId);
            return ResponseEntity.ok(carrera);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al obtener el usuario: " + e.getMessage());
        }
    }

    @GetMapping("/filtrarubicacionexacta")
    public ResponseEntity<List<Carrera>> filtrarCarrerasPorUbicacion(
            @RequestParam String ciudad,
            @RequestParam String region,
            @RequestParam String pais
    ) {
        List<Carrera> carreras = carreraService.obtenerCarrerasPorUbicacion(ciudad, region, pais);
        if (carreras != null && !carreras.isEmpty()) {
            return ResponseEntity.ok(carreras);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
