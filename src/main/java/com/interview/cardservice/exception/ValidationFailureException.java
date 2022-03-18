package com.interview.cardservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidationFailureException extends RuntimeException {
    public ValidationFailureException(String exception) {
        super(exception);
    }
}