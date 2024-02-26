package com.first_project.demo.application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.first_project.demo.application.request.SigninRequest;
import com.first_project.demo.application.response.AppResponse;
import com.first_project.demo.application.response.AuthResponse;
import com.first_project.demo.domain.ports.inbound.AuthUsecase;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthUsecase authUsecase;
    
    @PostMapping("/signin")
    public ResponseEntity<AppResponse<AuthResponse>> authSignin(@RequestBody @Valid SigninRequest signinRequest) {
        return authUsecase.authsignin(signinRequest);
    }

}
