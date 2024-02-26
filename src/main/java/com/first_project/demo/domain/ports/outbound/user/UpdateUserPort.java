package com.first_project.demo.domain.ports.outbound.user;

import com.first_project.demo.domain.model.Users;

public interface UpdateUserPort {
    Users updateUserById(Long id, Users updateUser);
}
