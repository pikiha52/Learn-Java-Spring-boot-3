package com.first_project.demo.domain.ports.inbound;

import org.springframework.http.ResponseEntity;

import com.first_project.demo.application.request.SigninRequest;
import com.first_project.demo.application.response.AppResponse;
import com.first_project.demo.application.response.AuthResponse;

public interface AuthUsecase {
    ResponseEntity<AppResponse<AuthResponse>> authsignin(SigninRequest signinRequest);
}
