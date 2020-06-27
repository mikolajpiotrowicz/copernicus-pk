package com.project.books.domain.excpetion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class PayloadErrorException extends RuntimeException {
    public PayloadErrorException(String message) {
        super(message);
    }
}