package com.first_project.demo.application.response;

import com.first_project.demo.domain.model.Users;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AuthResponse {

    private String accessToken;
    private String refreshToken;
    private Users users; 

}

