package com.first_project.demo.domain.services;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.first_project.demo.application.request.SigninRequest;
import com.first_project.demo.application.response.AppResponse;
import com.first_project.demo.application.response.AuthResponse;
import com.first_project.demo.common.customannotations.UseCase;
import com.first_project.demo.common.exception.UnauthorizedException;
import com.first_project.demo.domain.model.Users;
import com.first_project.demo.domain.ports.inbound.AuthUsecase;
import com.first_project.demo.domain.ports.outbound.user.GenerateTokenPort;
import com.first_project.demo.domain.ports.outbound.user.GetUserByEmailPort;

import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class AuthService implements AuthUsecase {

    private final GetUserByEmailPort getUserByEmailPort;
    private final PasswordEncoder passwordEncoder;
    private final GenerateTokenPort generateTokenPort;
    private static final Logger loggerException = LogManager.getLogger(AuthService.class);

    @Override
    public ResponseEntity<AppResponse<AuthResponse>> authsignin(SigninRequest signinRequest) {
        loggerException.info(
                "== start processing request login (check users by email, check match password, generate token) ==");
        final AppResponse<AuthResponse> appResponse = new AppResponse<>();
        final AuthResponse authResponse = new AuthResponse();

        loggerException.info("> check user by email request");
        Optional<Users> users = getUserByEmailPort.userByEmail(signinRequest.email);
        Users user = users.get();
        loggerException.info("> users data: ");
        loggerException.info(user);
        loggerException.info("< users data ");
        if (user == null)
            throw new UnauthorizedException("Email or password wronk.");

        loggerException.info("> check match password");
        Boolean checkMatchPassword = !passwordEncoder.matches(signinRequest.password, user.getPassword());
        if (checkMatchPassword)
            throw new UnauthorizedException("Email or password wronk.");
        loggerException.info(checkMatchPassword);
        loggerException.info("< check match password");

        Long expAccessToken = (long) (1000 * 60 * 30);
        Long expRefreshToken = (long) (24 * 60);
        authResponse.setUsers(user);

        String accessToken = generateTokenPort.generateToken(user, expAccessToken);
        String refreshToken = generateTokenPort.generateToken(user, expRefreshToken);

        loggerException.info("> generate token process: ");
        loggerException.info(accessToken);
        loggerException.info(refreshToken);
        loggerException.info("< generate token process");
        
        authResponse.setAccessToken(accessToken);
        authResponse.setRefreshToken(refreshToken);
        appResponse.setCode(HttpStatus.OK.value());
        appResponse.setMessage("Success");
        appResponse.setData(authResponse);

        loggerException.info("== finish processing login ==");
        return ResponseEntity.status(HttpStatus.OK).body(appResponse);
    }

}
