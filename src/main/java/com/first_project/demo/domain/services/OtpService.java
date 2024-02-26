package com.first_project.demo.domain.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.first_project.demo.application.request.RequestOtp;
import com.first_project.demo.application.response.AppResponse;
import com.first_project.demo.application.response.RequestOtpResponse;
import com.first_project.demo.common.customannotations.UseCase;
import com.first_project.demo.domain.ports.inbound.OtpUsecase;
// import com.first_project.demo.domain.ports.outbound.external.ExternalRequestPort;

import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class OtpService implements OtpUsecase {

    // private final ExternalRequestPort externalRequestPort;
    
    @Override
    public ResponseEntity<AppResponse<RequestOtpResponse>> requestOtp(RequestOtp requestOtp) {
        final AppResponse<RequestOtpResponse> appResponse = new AppResponse<>();

        appResponse.setCode(HttpStatus.CREATED.value());
        appResponse.setMessage("Success");
        return ResponseEntity.status(HttpStatus.CREATED).body(appResponse);
    }
    
}
