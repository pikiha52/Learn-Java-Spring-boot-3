package com.first_project.demo.domain.ports.outbound.user;

import com.first_project.demo.domain.model.Users;

public interface DeleteUserPort {
    Boolean deleteUserByIdPort(Users user);
}
