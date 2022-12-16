package com.example.error.exception;

import com.example.error.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class DrinkSoldOutException extends ResponseStatusException {

    public DrinkSoldOutException(HttpStatus status) {
        super(HttpStatus.BAD_REQUEST, ErrorMessage.DRINK_SOLD_OUT);
    }
}
