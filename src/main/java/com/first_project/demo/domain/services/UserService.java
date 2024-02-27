package com.first_project.demo.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.first_project.demo.application.request.CreateUser;
import com.first_project.demo.application.request.UpdateUser;
import com.first_project.demo.application.response.AppResponse;
import com.first_project.demo.application.response.CreateUserResponse;
import com.first_project.demo.common.customannotations.UseCase;
import com.first_project.demo.domain.model.Users;
import com.first_project.demo.domain.ports.inbound.UserUsecase;
import com.first_project.demo.domain.ports.outbound.user.CreateUserPort;
import com.first_project.demo.domain.ports.outbound.user.DeleteUserPort;
import com.first_project.demo.domain.ports.outbound.user.ListUsersPort;
import com.first_project.demo.domain.ports.outbound.user.ShowUserByIdPort;
import com.first_project.demo.domain.ports.outbound.user.UpdateUserPort;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class UserService implements UserUsecase {

    private final CreateUserPort createUserPort;
    private final ListUsersPort listUsersPort;
    private final ShowUserByIdPort showUserByIdPort;
    private final UpdateUserPort updateUserPort;
    private final DeleteUserPort deleteUserPort;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<AppResponse<Users>> createUser(CreateUser createUserContract) {
        final AppResponse<Users> appResponse = new AppResponse<>();
        Users users = new Users();
        users.setFullName(createUserContract.fullName);
        users.setEmail(createUserContract.email);
        users.setPhoneNumber(createUserContract.phoneNumber);
        users.setPassword(passwordEncoder.encode(createUserContract.password));
        users.setUsername(createUserContract.username);
        users.setRoles(createUserContract.roles);

        int httpStatus = HttpStatus.CREATED.value();
        try {
            CreateUserResponse createURes = createUserPort.createUser(users);
            appResponse.setCode(httpStatus);
            appResponse.setMessage("Created");
            appResponse.setData(createURes);
        } catch (Exception e) {
            httpStatus = HttpStatus.BAD_REQUEST.value();
            appResponse.setCode(httpStatus);
            appResponse.setErrorMessage("Failed create user");
            appResponse.setMessage(e.getMessage());
        }

        return ResponseEntity.status(httpStatus).body(appResponse);
    }

    @Override
    public ResponseEntity<AppResponse<List<Users>>> listUsers() {
        final AppResponse<List<Users>> appResponse = new AppResponse<>();
        List<Users> users = listUsersPort.getAllUsers();

        appResponse.setCode(HttpStatus.OK.value());
        appResponse.setMessage("Success");
        appResponse.setData(users);
        return ResponseEntity.status(HttpStatus.OK).body(appResponse);
    }

    @Override
    @Transactional
    public ResponseEntity<AppResponse<Users>> updateUser(Long id, UpdateUser updateUser) {
        final AppResponse<Users> appResponse = new AppResponse<>();
        appResponse.setCode(HttpStatus.OK.value());
        appResponse.setMessage("Success");
        Optional<Users> checkUser = showUserByIdPort.findByIdUser(id);
        if (checkUser.isEmpty()) {
            appResponse.setCode(HttpStatus.NOT_FOUND.value());
            appResponse.setMessage("user not found");
            appResponse.setErrorMessage("Not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(appResponse);
        }

        Users data = checkUser.get();
        data.setFullName(updateUser.fullName);
        data.setEmail(updateUser.email);
        data.setPhoneNumber(updateUser.phoneNumber);
        // data.setPassword(passwordEncoder.encode(updateUser.password));
        Users user = updateUserPort.updateUserById(id, data);

        appResponse.setData(user);
        return ResponseEntity.status(HttpStatus.OK).body(appResponse);
    }

    @Override
    public ResponseEntity<AppResponse<String>> deleteUser(Long id, Users userRequest) {
        final AppResponse<String> appResponse = new AppResponse<>();
        appResponse.setCode(HttpStatus.OK.value());
        appResponse.setMessage("Success");

        Optional<Users> user = showUserByIdPort.findByIdUser(id);
        if (user.isEmpty()) {
            appResponse.setCode(HttpStatus.NOT_FOUND.value());
            appResponse.setMessage("Not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(appResponse);
        }

        if (user.get().getId().equals(userRequest.getId())) {
            appResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            appResponse.setMessage("failed deleted");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(appResponse);
        } 

        Boolean isDeleted = deleteUserPort.deleteUserByIdPort(user.get());
        if (!isDeleted) {
            appResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            appResponse.setMessage("failed deleted");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(appResponse);
        } 
        
        return ResponseEntity.status(HttpStatus.OK).body(appResponse);
    }

}
