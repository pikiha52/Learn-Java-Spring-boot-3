package com.first_project.demo.domain.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.first_project.demo.application.response.AppResponse;
import com.first_project.demo.common.customannotations.UseCase;
import com.first_project.demo.domain.ports.inbound.HealthCheckUsecase;

@UseCase
public class HealthCheckService implements HealthCheckUsecase {

    @Override
    public ResponseEntity<AppResponse<String>> heathCheck() {
        final AppResponse<String> appResponse = new AppResponse<>();
        appResponse.setCode(HttpStatus.OK.value());
        appResponse.setMessage("Service is running well!");
        appResponse.setErrorMessage(null);
        return ResponseEntity.ok().body(appResponse);
    }
    
}
