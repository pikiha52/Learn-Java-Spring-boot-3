package com.first_project.demo.infrastructure.databases.user;

import java.util.List;

import org.springframework.stereotype.Component;

import com.first_project.demo.domain.model.Users;
import com.first_project.demo.domain.ports.outbound.user.ListUsersPort;
import com.first_project.demo.infrastructure.repositories.UserRepositories;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GetAllUserAdapter implements ListUsersPort {
    private final UserRepositories userRepositories;

    @Override
    public List<Users> getAllUsers() {
        List<Users> users = userRepositories.findAll();
        return users;
    }
    
}
