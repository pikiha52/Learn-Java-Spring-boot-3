package com.first_project.demo.infrastructure.databases.user;

import org.springframework.stereotype.Component;

import com.first_project.demo.domain.model.Users;
import com.first_project.demo.domain.ports.outbound.user.DeleteUserPort;
import com.first_project.demo.infrastructure.repositories.UserRepositories;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DeleteUserAdapter implements DeleteUserPort {

    private final UserRepositories userRepositories;
    
    @Override
    public Boolean deleteUserByIdPort(Users user) {
        Boolean isDeleted;
        try {
            userRepositories.deleteById(user.getId());
            isDeleted = true;
        } catch (Exception exception) {
            isDeleted = false;
        }

        return isDeleted;
    }
    
}
