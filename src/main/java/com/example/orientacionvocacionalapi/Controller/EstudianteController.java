package com.example.orientacionvocacionalapi.Controller;
import com.example.orientacionvocacionalapi.model.entity.Estudiante;
import com.example.orientacionvocacionalapi.service.impl.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {
    @Autowired
    private EstudianteService estudianteService;

    @GetMapping("/listarestudiantes")
    public ResponseEntity<List<Estudiante>> listarEstudiantes() {
        List<Estudiante> estudiantes = estudianteService.listarTodosLosEstudiantes();
        return ResponseEntity.ok(estudiantes);
    }
}