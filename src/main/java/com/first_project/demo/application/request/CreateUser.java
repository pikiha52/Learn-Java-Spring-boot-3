package com.first_project.demo.application.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateUser {
    
    @NotBlank(message = "property fullName is not found")
    @NotNull(message = "property fullName is required")
    public String fullName;

    @NotBlank(message = "property email is not found")
    @NotNull(message = "property email is required")
    public String email;

    @NotBlank(message = "property phoneNumber is not found")
    @NotNull(message = "property phoneNumber is required")
    public String phoneNumber;

    @NotBlank(message = "property password is not found")
    @NotNull(message = "property password is required")
    public String password;

    @NotBlank(message = "property roles is not found")
    @NotNull(message = "property roles is required")
    public String roles;

    @NotBlank(message = "property username is not found")
    @NotNull(message = "property username is required")
    public String username;

}
