package com.first_project.demo.infrastructure.databases.user;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.first_project.demo.domain.model.Users;
import com.first_project.demo.domain.ports.outbound.user.GetUserByEmailPort;
import com.first_project.demo.infrastructure.repositories.UserRepositories;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GetUserByEmailAdapter implements GetUserByEmailPort {

    final private UserRepositories userRepositories;
    
    @Override
    public Optional<Users> userByEmail(String email) {
        return userRepositories.findByEmail(email);
    }
    
}
