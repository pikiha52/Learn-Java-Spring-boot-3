package com.first_project.demo.domain.ports.inbound;

import org.springframework.http.ResponseEntity;

import com.first_project.demo.application.request.RequestOtp;
import com.first_project.demo.application.response.AppResponse;
import com.first_project.demo.application.response.RequestOtpResponse;

public interface OtpUsecase {
    ResponseEntity<AppResponse<RequestOtpResponse>> requestOtp(RequestOtp requestOtp); 
}
