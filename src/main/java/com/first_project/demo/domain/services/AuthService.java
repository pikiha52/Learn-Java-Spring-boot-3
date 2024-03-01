package com.first_project.demo.domain.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;

import com.first_project.demo.application.request.SigninRequest;
import com.first_project.demo.application.response.AppResponse;
import com.first_project.demo.application.response.AuthResponse;
import com.first_project.demo.common.customannotations.UseCase;
import com.first_project.demo.domain.model.Users;
import com.first_project.demo.domain.ports.inbound.AuthUsecase;
import com.first_project.demo.domain.ports.outbound.authentication.AuthenticationPort;
import com.first_project.demo.domain.ports.outbound.user.GenerateTokenPort;
import com.first_project.demo.domain.ports.outbound.user.GetUserByUsernamePort;

import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class AuthService implements AuthUsecase {

    private static final Logger loggerException = LogManager.getLogger(AuthService.class);
    private final AuthenticationPort authenticationPort;
    private final GenerateTokenPort generateTokenPort;
    private final GetUserByUsernamePort getUserByUsernamePort;

    @Override
    public ResponseEntity<AppResponse<AuthResponse>> authsignin(SigninRequest signinRequest) {
        HttpStatus httpStatus = HttpStatus.OK;
        loggerException.info(
                "== start processing request login (check users by email, check match password, generate token) ==");
        final AppResponse<AuthResponse> appResponse = new AppResponse<>();
        final AuthResponse authResponse = new AuthResponse();

        try {
            Authentication authentication = authenticationPort.authentication(signinRequest.username, signinRequest.password);
            Users user = getUserByUsernamePort.getUserByUsername(authentication.getName()).get();

            Long expAccessToken = (long) (1000 * 60 * 30);
            Long expRefreshToken = (long) (24 * 60);

            authResponse.setAccessToken(generateTokenPort.generateToken(user, expAccessToken));
            authResponse.setRefreshToken(generateTokenPort.generateToken(user, expRefreshToken));
            authResponse.setUsers(user);
            appResponse.setData(authResponse);
        } catch (BadCredentialsException exception) {
            httpStatus = HttpStatus.UNAUTHORIZED;
            appResponse.setCode(httpStatus.value());
            appResponse.setErrorMessage("Unauthorized");
            appResponse.setMessage(exception.getMessage());
        }

        return ResponseEntity.status(httpStatus).body(appResponse);
    }

}
