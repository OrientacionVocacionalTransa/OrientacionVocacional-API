package com.example.orientacionvocacionalapi.service.impl;

import com.example.orientacionvocacionalapi.dto.PlanDTO;
import com.example.orientacionvocacionalapi.model.entity.Plan;
import com.example.orientacionvocacionalapi.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlanService {
    @Autowired
    private PlanRepository planRepository;

    public List<PlanDTO> getAllPlans() {
        List<Plan> plans = planRepository.findAll();
        return plans.stream()
                .map(plan -> new PlanDTO(plan.getId(),plan.getName(), plan.getPrice(), plan.getDescription()))
                .collect(Collectors.toList());
    }
}