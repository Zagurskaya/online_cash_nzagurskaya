package com.gmail.zagurskaya.service.exception;

public class RateCBServiceImplException extends RuntimeException {

    public RateCBServiceImplException(String message) {

        super(message);
    }
    public RateCBServiceImplException(String message, Throwable e) {

        super(message, e);
    }
}
