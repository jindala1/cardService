package com.interview.cardservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoRecordFoundException extends RuntimeException {
    public NoRecordFoundException(final String message) {
        super(message);
    }
}