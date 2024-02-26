package com.first_project.demo.domain.ports.outbound.user;

import java.util.Optional;

import com.first_project.demo.domain.model.Users;

public interface GetUserByEmailPort {
    Optional<Users> userByEmail(String email);
}
