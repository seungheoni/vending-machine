package com.example.error.exception;

import com.example.error.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class DrinkNotExistExcepion extends ResponseStatusException {

    public DrinkNotExistExcepion(HttpStatus status) {
        super(HttpStatus.NOT_FOUND, ErrorMessage.DRINK_NOT_EXIST);
    }
}
