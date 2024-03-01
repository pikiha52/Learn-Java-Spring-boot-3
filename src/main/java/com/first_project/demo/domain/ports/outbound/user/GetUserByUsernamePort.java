package com.first_project.demo.domain.ports.outbound.user;

import java.util.Optional;

import com.first_project.demo.domain.model.Users;

public interface GetUserByUsernamePort {
    Optional<Users> getUserByUsername(String username);
}
