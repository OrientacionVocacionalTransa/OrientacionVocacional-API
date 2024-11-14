package com.example.orientacionvocacionalapi.Controller;
import com.example.orientacionvocacionalapi.dto.AdviserDTO;
import com.example.orientacionvocacionalapi.model.entity.Adviser;
import com.example.orientacionvocacionalapi.service.impl.AdviserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/adviser")
public class AdviserController {

    @Autowired
    private AdviserService adviserService;

    @GetMapping("/profile")
    public ResponseEntity<?> getAdvisorProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Optional<Adviser> adviser = adviserService.getAdvisorProfileByEmail(email);

        if (adviser.isPresent()) {
            return ResponseEntity.ok(adviser.get());
        } else {
            return ResponseEntity.status(404).body("{\"error\": \"Perfil del asesor no encontrado.\"}");
        }
    }

    @GetMapping("/listAdvisors")
    public ResponseEntity<List<AdviserDTO>> listAdvisors() {
        List<AdviserDTO> advisors = adviserService.listAllAdvisors();
        return ResponseEntity.ok(advisors);
    }

}