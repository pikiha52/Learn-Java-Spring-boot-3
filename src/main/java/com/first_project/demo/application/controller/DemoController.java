package com.first_project.demo.application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.first_project.demo.domain.ports.inbound.HealthCheckUsecase;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/health-check")
public class DemoController {
    
    private final HealthCheckUsecase healthCheckUsecase;

    @GetMapping
    public ResponseEntity<?> healthCheck() {
        return healthCheckUsecase.heathCheck();
    }

}
