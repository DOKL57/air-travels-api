package com.dokl57.airtravelsapi.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = org.springframework.http.HttpStatus.BAD_REQUEST)
public class ValidationException extends RuntimeException{
    public ValidationException(String message) {
        super(message);
    }
}
