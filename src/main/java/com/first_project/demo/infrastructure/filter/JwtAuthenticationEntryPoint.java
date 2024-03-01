package com.first_project.demo.infrastructure.filter;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.first_project.demo.application.response.AppResponse;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        final AppResponse<String> appResponse = new AppResponse<>();

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        String message;
        if (authException.getCause() != null) {
            message = authException.getCause().toString() + " " + authException.getMessage();
        } else {
            message = authException.getMessage();
        }

        appResponse.setCode(HttpStatus.UNAUTHORIZED.value());
        appResponse.setMessage("Unauthorized");
        appResponse.setErrorMessage(message);
        byte[] body = new ObjectMapper().writeValueAsBytes(appResponse);
        response.getOutputStream().write(body);

    }

}
