package com.example.orientacionvocacionalapi.Controller;
import com.example.orientacionvocacionalapi.dto.EstudianteDTO;
import com.example.orientacionvocacionalapi.model.entity.Estudiante;
import com.example.orientacionvocacionalapi.service.impl.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {
    @Autowired
    private EstudianteService estudianteService;

    @GetMapping("/listarestudiantes")
    public ResponseEntity<List<EstudianteDTO>> listarEstudiantes() {
        List<EstudianteDTO> estudiantes = estudianteService.listarTodosLosEstudiantes();
        return ResponseEntity.ok(estudiantes);
    }
    @GetMapping("/perfil")
    public ResponseEntity<?> obtenerPerfilAsesor() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        String email = authentication.getName();

        Optional<Estudiante> estudiante = estudianteService.obtenerPerfilEstudiantePorEmail(email);



        if (estudiante.isPresent()) {
            return ResponseEntity.ok(estudiante.get());
        } else {
            return ResponseEntity.status(404).body("{\"error\": \"Perfil del Estudiante no encontrado.\"}");
        }
    }
}