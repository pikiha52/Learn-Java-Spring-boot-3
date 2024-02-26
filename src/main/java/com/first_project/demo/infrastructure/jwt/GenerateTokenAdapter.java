package com.first_project.demo.infrastructure.jwt;

import org.springframework.stereotype.Component;

import com.first_project.demo.domain.model.Users;
import com.first_project.demo.domain.ports.outbound.user.GenerateTokenPort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GenerateTokenAdapter implements GenerateTokenPort  {

    private final JwtService jwtService;

    @Override
    public String generateToken(Users user, Long exp) {
        return jwtService.generateToken(user.getEmail(), exp);
    }
    
}
