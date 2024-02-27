package com.first_project.demo.domain.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.first_project.demo.application.request.SigninRequest;
import com.first_project.demo.application.response.AppResponse;
import com.first_project.demo.application.response.AuthResponse;
import com.first_project.demo.common.customannotations.UseCase;
import com.first_project.demo.domain.ports.inbound.AuthUsecase;

import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class AuthService implements AuthUsecase {

    private static final Logger loggerException = LogManager.getLogger(AuthService.class);

    @Override
    public ResponseEntity<AppResponse<AuthResponse>> authsignin(SigninRequest signinRequest) {
        loggerException.info(
                "== start processing request login (check users by email, check match password, generate token) ==");
        final AppResponse<AuthResponse> appResponse = new AppResponse<>();
        final AuthResponse authResponse = new AuthResponse();
        
        appResponse.setData(authResponse);
        return ResponseEntity.status(HttpStatus.OK).body(appResponse);
    }

}
