package com.RemoteMode.internshipjava2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException{

    //String errorMessage;

    public BadRequestException(String errorMessage, Throwable e) {
        super(errorMessage, e);
    }

    public BadRequestException(String errorMessage) {
        super(errorMessage);
    }
}
