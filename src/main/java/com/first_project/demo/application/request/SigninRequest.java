package com.first_project.demo.application.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public class SigninRequest {

    @NotBlank(message = "property email is not found")
    @NotEmpty(message = "property email is required")
    @Email(message = "email not supported")
    public String email;

    @NotBlank(message = "property password is not found")
    @NotEmpty(message = "property password is required")
    public String password;
    
}
