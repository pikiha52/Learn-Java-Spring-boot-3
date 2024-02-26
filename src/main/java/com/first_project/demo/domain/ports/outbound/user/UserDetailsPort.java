package com.first_project.demo.domain.ports.outbound.user;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsPort {
    UserDetails userDetailByEmailPort(String email);
}
