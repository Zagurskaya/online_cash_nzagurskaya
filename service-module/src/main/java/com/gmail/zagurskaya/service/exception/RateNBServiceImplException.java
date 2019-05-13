package com.gmail.zagurskaya.service.exception;

public class RateNBServiceImplException extends RuntimeException {

    public RateNBServiceImplException(String message) {

        super(message);
    }
    public RateNBServiceImplException(String message, Throwable e) {

        super(message, e);
    }
}
