package com.example.error.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CashEmptyException extends ResponseStatusException {

    public CashEmptyException(HttpStatus status) {
        super(status);
    }

    public CashEmptyException(HttpStatus status, String reason) {
        super(status, reason);
    }

    public CashEmptyException(HttpStatus status, String reason, Throwable cause) {
        super(status, reason, cause);
    }

    public CashEmptyException(int rawStatusCode, String reason, Throwable cause) {
        super(rawStatusCode, reason, cause);
    }
}
