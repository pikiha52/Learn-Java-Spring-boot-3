package com.first_project.demo.domain.ports.outbound.user;

import java.util.Optional;

import com.first_project.demo.domain.model.Users;

public interface ShowUserByIdPort {
    Optional<Users> findByIdUser(Long id);
}
