package com.first_project.demo.domain.ports.outbound.user;

import java.util.List;

import com.first_project.demo.domain.model.Users;

public interface ListUsersPort {
    List<Users> getAllUsers();
}
