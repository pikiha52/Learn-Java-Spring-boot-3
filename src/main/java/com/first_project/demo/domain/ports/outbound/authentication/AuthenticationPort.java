package com.first_project.demo.domain.ports.outbound.authentication;

import org.springframework.security.core.Authentication;

public interface AuthenticationPort {
    Authentication authentication(String username, String password);
}
