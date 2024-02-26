package com.first_project.demo.common.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.first_project.demo.application.response.AppResponse;

import io.jsonwebtoken.ExpiredJwtException;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomExceptionHandler {

    private static final Logger loggerException = LogManager.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<AppResponse<String>> handleBadRequestException(MethodArgumentNotValidException ex) {
        final AppResponse<String> appResponse = new AppResponse<>();
        List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());

        appResponse.setCode(HttpStatus.BAD_REQUEST.value());
        appResponse.setErrorMessage("Bad request.");
        appResponse.setErrorMessage(errors.getFirst());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(appResponse);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<AppResponse<String>> resourceNotFoundException(ResourceNotFoundException ex,
            WebRequest request) {
        final AppResponse<String> appResponse = new AppResponse<>();
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        loggerException.error(ex);

        appResponse.setCode(HttpStatus.NOT_FOUND.value());
        appResponse.setErrorMessage("Not found");
        appResponse.setMessage(errorDetails.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(appResponse);
    }

    @ExceptionHandler({ GlobalException.class })
    public ResponseEntity<AppResponse<String>> globalExceptionHandler(GlobalException ex, WebRequest request) {
        final AppResponse<String> appResponse = new AppResponse<>();
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        loggerException.error(ex);

        appResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        appResponse.setErrorMessage("Internal server error");
        appResponse.setMessage(errorDetails.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(appResponse);
    }

    @ExceptionHandler({ UnauthorizedException.class })
    public ResponseEntity<AppResponse<String>> unauthorizedExceptionHandler(UnauthorizedException ex,
            WebRequest request) {
        final AppResponse<String> appResponse = new AppResponse<>();

        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        loggerException.error(ex);

        appResponse.setCode(HttpStatus.UNAUTHORIZED.value());
        appResponse.setErrorMessage("Unauthorized");
        appResponse.setMessage(errorDetails.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(appResponse);
    }

    @ExceptionHandler({ BadRequestExceptionCustom.class })
    public ResponseEntity<AppResponse<String>> badRequestExceptionHandler(BadRequestExceptionCustom ex,
            WebRequest request) {
        final AppResponse<String> appResponse = new AppResponse<>();

        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        loggerException.error(ex);

        appResponse.setCode(HttpStatus.BAD_REQUEST.value());
        appResponse.setErrorMessage("Bad request");
        appResponse.setMessage(errorDetails.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(appResponse);
    }

    @ExceptionHandler({ NotFoundException.class })
    public ResponseEntity<AppResponse<String>> notFoundExceptionHandler(NotFoundException ex, WebRequest request) {
        final AppResponse<String> appResponse = new AppResponse<>();

        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        loggerException.error(ex);

        appResponse.setCode(HttpStatus.NOT_FOUND.value());
        appResponse.setErrorMessage("Not found");
        appResponse.setMessage(errorDetails.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(appResponse);
    }

    @ExceptionHandler({ ExpiredJwtException.class })
    public ResponseEntity<AppResponse<String>> handleExpiredJwtException(ExpiredJwtException ex, WebRequest request) {
        final AppResponse<String> appResponse = new AppResponse<>();
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));

        appResponse.setCode(HttpStatus.UNAUTHORIZED.value());
        appResponse.setMessage("Unauthorized");
        appResponse.setErrorMessage(errorDetails.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(appResponse);
    }

}
