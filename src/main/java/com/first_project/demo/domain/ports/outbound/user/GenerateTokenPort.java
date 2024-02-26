package com.first_project.demo.domain.ports.outbound.user;

import com.first_project.demo.domain.model.Users;

public interface GenerateTokenPort {
    String generateToken(Users user, Long exp);
}
