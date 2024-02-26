package com.first_project.demo.domain.ports.inbound;

import org.springframework.http.ResponseEntity;

import com.first_project.demo.application.response.AppResponse;

public interface HealthCheckUsecase {
    ResponseEntity<AppResponse<String>> heathCheck();
}
