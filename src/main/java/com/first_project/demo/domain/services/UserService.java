package com.first_project.demo.domain.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ldap.NameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.first_project.demo.application.request.CreateUser;
import com.first_project.demo.application.request.UpdateUser;
import com.first_project.demo.application.response.AppResponse;
import com.first_project.demo.application.response.CreateUserResponse;
import com.first_project.demo.common.customannotations.UseCase;
import com.first_project.demo.domain.model.Users;
import com.first_project.demo.domain.model.UsersLdap;
import com.first_project.demo.domain.ports.inbound.UserUsecase;
import com.first_project.demo.domain.ports.outbound.user.CreateUserPort;
import com.first_project.demo.domain.ports.outbound.user.DeleteUserPort;
import com.first_project.demo.domain.ports.outbound.user.ListUsersPort;
import com.first_project.demo.domain.ports.outbound.user.ShowUserByIdPort;
import com.first_project.demo.domain.ports.outbound.user.ShowUserLdapPort;
import com.first_project.demo.domain.ports.outbound.user.UpdateUserPort;
import com.first_project.demo.domain.ports.outbound.user.UserLdapsPort;

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
    private final UserLdapsPort userLdapsPort;
    private final ShowUserLdapPort showUserLdapPort;

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
    public ResponseEntity<AppResponse<Users>> showUser(Long id) {
        final AppResponse<Users> appResponse = new AppResponse<>();
        Users data = new Users();
        HttpStatus httpStatus = HttpStatus.OK;

        try {
            Optional<Users> user = showUserByIdPort.findByIdUser(id);
            data = user.get();
            appResponse.setCode(httpStatus.value());
            appResponse.setMessage("Success");
            appResponse.setData(data);
        } catch (Exception e) {
            httpStatus = HttpStatus.NOT_FOUND;
            appResponse.setCode(httpStatus.value());
            appResponse.setMessage("User not found");
            appResponse.setErrorMessage("Not found");
        }

        return ResponseEntity.status(httpStatus).body(appResponse);
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

    @Override
    public ResponseEntity<AppResponse<List<UsersLdap>>> ldapUsers() {
        final AppResponse<List<UsersLdap>> appResponse = new AppResponse<>();
        List<UsersLdap> usersLdaps = userLdapsPort.allUserLdaps();

        appResponse.setCode(HttpStatus.OK.value());
        appResponse.setMessage("Succes");
        appResponse.setData(usersLdaps);
        return ResponseEntity.status(HttpStatus.OK).body(appResponse);
    }

    @Override
    public ResponseEntity<AppResponse<UsersLdap>> showUserLdap(String uid) {
        final AppResponse<UsersLdap> appResponse = new AppResponse<>();
        HttpStatus httpStatus = HttpStatus.OK;
        appResponse.setCode(httpStatus.value());
        appResponse.setMessage("Success");

        Optional<UsersLdap> userLdap = showUserLdapPort.findByUidLdap(uid);
        if (userLdap.isEmpty()) {
            httpStatus = HttpStatus.NOT_FOUND;
            appResponse.setCode(httpStatus.value());
            appResponse.setMessage("user not found in ldap");
            appResponse.setErrorMessage("Not found");
        } else {
            appResponse.setData(userLdap.get());
        }

        return ResponseEntity.status(httpStatus).body(appResponse);
    }

}
