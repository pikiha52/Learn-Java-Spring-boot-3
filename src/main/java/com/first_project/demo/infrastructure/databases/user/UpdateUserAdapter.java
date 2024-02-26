package com.first_project.demo.infrastructure.databases.user;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.first_project.demo.domain.model.Users;
import com.first_project.demo.domain.ports.outbound.user.UpdateUserPort;
import com.first_project.demo.infrastructure.repositories.UserRepositories;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UpdateUserAdapter implements UpdateUserPort {

    private final UserRepositories userRepositories;

    @Override
    @Transactional
    public Users updateUserById(Long id, Users updateUser) {
        return userRepositories.save(updateUser);
    }
    
}
