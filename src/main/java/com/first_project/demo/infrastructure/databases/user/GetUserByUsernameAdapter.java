package com.first_project.demo.infrastructure.databases.user;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.first_project.demo.domain.model.Users;
import com.first_project.demo.domain.ports.outbound.user.GetUserByUsernamePort;
import com.first_project.demo.infrastructure.repositories.UserRepositories;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GetUserByUsernameAdapter implements GetUserByUsernamePort {

    private final UserRepositories userRepositories;
    
    @Override
    public Optional<Users> getUserByUsername(String username) {
        return userRepositories.findByUsername(username);
    }
    
}
