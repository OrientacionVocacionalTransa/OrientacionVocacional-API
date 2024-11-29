package com.example.orientacionvocacionalapi.Controller;

import com.example.orientacionvocacionalapi.dto.PlanDTO;
import com.example.orientacionvocacionalapi.service.impl.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/plans")
public class PlanController {

    @Autowired
    private PlanService planService;

    @GetMapping
    public List<PlanDTO> getPlans() {
        return planService.getAllPlans();
    }
}
