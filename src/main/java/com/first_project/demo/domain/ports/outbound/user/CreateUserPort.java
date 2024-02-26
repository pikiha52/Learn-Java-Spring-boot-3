package com.first_project.demo.domain.ports.outbound.user;

import com.first_project.demo.application.response.CreateUserResponse;
import com.first_project.demo.domain.model.Users;

public interface CreateUserPort {
    CreateUserResponse createUser(Users users);
}
