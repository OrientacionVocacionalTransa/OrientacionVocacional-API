package com.example.orientacionvocacionalapi.Controller;

import com.example.orientacionvocacionalapi.dto.AdvisoryDTO;
import com.example.orientacionvocacionalapi.model.entity.Advisory;
import com.example.orientacionvocacionalapi.service.impl.AdvisoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/advisories")
public class AdvisoryController {

    @Autowired
    private AdvisoryService advisoryService;

    @PostMapping("/create")
    public ResponseEntity<Advisory> createAdvisory(@Validated @RequestBody AdvisoryDTO advisoryDTO) {
        Advisory advisory = advisoryService.createAdvisory(advisoryDTO);
        return ResponseEntity.ok(advisory);
    }
}