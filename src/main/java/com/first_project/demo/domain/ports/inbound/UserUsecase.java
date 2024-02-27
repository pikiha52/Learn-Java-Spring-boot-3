package com.first_project.demo.domain.ports.inbound;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.first_project.demo.application.request.CreateUser;
import com.first_project.demo.application.request.UpdateUser;
import com.first_project.demo.application.response.AppResponse;
import com.first_project.demo.domain.model.Users;

public interface UserUsecase {
    ResponseEntity<AppResponse<Users>> createUser(CreateUser createUserContract);
    ResponseEntity<AppResponse<List<Users>>> listUsers();
    ResponseEntity<AppResponse<Users>> updateUser(Long id, UpdateUser updateUser);
    ResponseEntity<AppResponse<String>> deleteUser(Long id, Users userRequest);
}
