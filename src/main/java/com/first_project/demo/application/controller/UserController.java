package com.first_project.demo.application.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.first_project.demo.application.request.CreateUser;
import com.first_project.demo.application.request.UpdateUser;
import com.first_project.demo.application.response.AppResponse;
import com.first_project.demo.common.exception.BadRequestExceptionCustom;
import com.first_project.demo.domain.model.Users;
import com.first_project.demo.domain.model.UsersLdap;
import com.first_project.demo.domain.ports.inbound.UserUsecase;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    
    private final UserUsecase userUsecase;

    @PostMapping()
    public ResponseEntity<AppResponse<Users>> createUser(@RequestBody @Valid CreateUser createUser) {
        return userUsecase.createUser(createUser);
    }

    @GetMapping()
    public ResponseEntity<AppResponse<List<Users>>> listUsers() {
        return userUsecase.listUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppResponse<Users>> showUser(@PathVariable String id) {
        if (id.equals(":id")) throw new BadRequestExceptionCustom("Id not valid.");
        return userUsecase.showUser(Long.parseLong(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppResponse<Users>> updateUser(@PathVariable String id, @RequestBody @Valid UpdateUser updateUser) {
        if (id.equals(":id")) throw new BadRequestExceptionCustom("Id not valid.");
        return userUsecase.updateUser(Long.parseLong(id), updateUser);
    } 
    
    @DeleteMapping("/{id}")
    public ResponseEntity<AppResponse<String>> deleteUser(@PathVariable String id, @RequestAttribute("UserData") Users user) {
        if (id.equals(":id")) throw new BadRequestExceptionCustom("Id not valid.");
        return userUsecase.deleteUser(Long.parseLong(id), user);
    }
    
    @GetMapping("/ldaps")
    public ResponseEntity<AppResponse<List<UsersLdap>>> listUserLdaps() {
        return userUsecase.ldapUsers();
    }
    
    @GetMapping("/ldaps/{uid}")
    public ResponseEntity<AppResponse<UsersLdap>> userLdapShow(@PathVariable String uid) {
        if (uid.equals(":uid")) throw new BadRequestExceptionCustom("Uid not valid.");
        return userUsecase.showUserLdap(uid);
    }

}
