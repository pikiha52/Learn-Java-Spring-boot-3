package com.first_project.demo.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestExceptionCustom extends RuntimeException {
    
    public BadRequestExceptionCustom(String message) {
        super(message);
    }

}
