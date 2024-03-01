package com.first_project.demo.infrastructure.authentication;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.first_project.demo.domain.ports.outbound.authentication.AuthenticationPort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthenticationAdapter implements AuthenticationPort {

    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication authentication(String username, String password) {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        return auth;
    }
    
}
