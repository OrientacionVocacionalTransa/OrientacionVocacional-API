package com.example.orientacionvocacionalapi.Controller;
import com.example.orientacionvocacionalapi.model.entity.Asesor;
import com.example.orientacionvocacionalapi.service.impl.AsesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/asesores")
public class AsesorController {

    @Autowired
    private AsesorService asesorService;

    @GetMapping("/perfil")
    public ResponseEntity<?> obtenerPerfilAsesor() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        String email = authentication.getName();


        Optional<Asesor> asesor = asesorService.obtenerPerfilAsesorPorEmail(email);


        if (asesor.isPresent()) {
            return ResponseEntity.ok(asesor.get());
        } else {
            return ResponseEntity.status(404).body("{\"error\": \"Perfil del asesor no encontrado.\"}");
        }
    }


}