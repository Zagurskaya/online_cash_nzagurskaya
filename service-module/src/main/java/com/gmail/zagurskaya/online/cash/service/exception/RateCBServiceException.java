package com.gmail.zagurskaya.online.cash.service.exception;

public class RateCBServiceException extends RuntimeException {

    public RateCBServiceException(String message) {

        super(message);
    }
    public RateCBServiceException(String message, Throwable e) {

        super(message, e);
    }
}
