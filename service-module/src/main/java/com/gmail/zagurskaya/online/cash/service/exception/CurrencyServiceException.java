package com.gmail.zagurskaya.online.cash.service.exception;

public class CurrencyServiceException extends RuntimeException {

    public CurrencyServiceException(String message) {

        super(message);
    }
    public CurrencyServiceException(String message, Throwable e) {

        super(message, e);
    }
}
