package com.first_project.demo.application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.first_project.demo.application.request.RequestOtp;
import com.first_project.demo.application.response.AppResponse;
import com.first_project.demo.application.response.RequestOtpResponse;
import com.first_project.demo.domain.ports.inbound.OtpUsecase;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/otp")
@RequiredArgsConstructor
public class OtpController {
    
    private final OtpUsecase otpUsecase;

    @PostMapping("/request")
    public ResponseEntity<AppResponse<RequestOtpResponse>> requestOtp(@RequestBody @Valid RequestOtp requestOtpContract) {
        return otpUsecase.requestOtp(requestOtpContract);
    }

}
