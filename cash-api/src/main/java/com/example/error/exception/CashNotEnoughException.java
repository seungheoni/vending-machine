package com.example.error.exception;

import com.example.error.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CashNotEnoughException extends ResponseStatusException {

    public CashNotEnoughException() {
        super(HttpStatus.BAD_REQUEST, ErrorMessage.CASH_NOT_ENOUGH);
    }
}
