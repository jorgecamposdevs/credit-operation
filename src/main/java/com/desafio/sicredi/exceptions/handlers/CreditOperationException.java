package com.desafio.sicredi.exceptions.handlers;

import lombok.Getter;

public class CreditOperationException extends RuntimeException {

    public CreditOperationException(String message) {
        super(message);
    }

    public CreditOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}