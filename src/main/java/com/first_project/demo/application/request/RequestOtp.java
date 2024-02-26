package com.first_project.demo.application.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public class RequestOtp {

    @NotBlank(message = "type not found")
    @NotEmpty(message = "type is required")
    public String type;

    @NotBlank(message = "morph not found")
    @NotEmpty(message = "morph is required")
    public String morph;

}
