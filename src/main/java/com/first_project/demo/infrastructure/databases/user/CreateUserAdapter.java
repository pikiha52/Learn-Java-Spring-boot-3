package com.first_project.demo.infrastructure.databases.user;

import org.springframework.stereotype.Component;

import com.first_project.demo.application.response.CreateUserResponse;
import com.first_project.demo.domain.model.Users;
import com.first_project.demo.domain.ports.outbound.user.CreateUserPort;
import com.first_project.demo.infrastructure.repositories.UserRepositories;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreateUserAdapter implements CreateUserPort {
    private final UserRepositories userRepositories;

    @Override
    public CreateUserResponse createUser(Users users) {
        final CreateUserResponse createUserResponse = new CreateUserResponse();
        Users createUser = userRepositories.save(users);
        createUserResponse.setId(createUser.getId());
        createUserResponse.setCreatedAt(createUser.getCreatedAt());
        return createUserResponse;
    }
    
}
