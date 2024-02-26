package com.first_project.demo.infrastructure.databases.user;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.first_project.demo.domain.model.Users;
import com.first_project.demo.domain.ports.outbound.user.ShowUserByIdPort;
import com.first_project.demo.infrastructure.repositories.UserRepositories;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ShowUserByIdAdapter implements ShowUserByIdPort {

    private final UserRepositories userRepositories;
    
    @Override
    public Optional<Users> findByIdUser(Long id) {    
        return userRepositories.findById(id);
    }
    
}
