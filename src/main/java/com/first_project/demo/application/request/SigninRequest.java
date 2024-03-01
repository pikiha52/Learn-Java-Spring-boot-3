package com.first_project.demo.application.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public class SigninRequest {

    @NotBlank(message = "property username is not found")
    @NotEmpty(message = "property username is required")
    public String username;

    @NotBlank(message = "property password is not found")
    @NotEmpty(message = "property password is required")
    public String password;
    
}
