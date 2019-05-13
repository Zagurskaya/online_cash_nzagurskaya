package com.gmail.zagurskaya.service.exception;

public class CurrencyServiceImplException extends RuntimeException {

    public CurrencyServiceImplException(String message) {

        super(message);
    }
    public CurrencyServiceImplException(String message, Throwable e) {

        super(message, e);
    }
}
