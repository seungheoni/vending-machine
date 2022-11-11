package com.example.error.exception;

import com.example.error.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CashEmptyException extends ResponseStatusException {

    public CashEmptyException() {
        super(HttpStatus.NOT_FOUND, ErrorMessage.CASH_EMPTY);
    }
}
