package com.gmail.zagurskaya.online.cash.service.exception;

public class RateNBServiceException extends RuntimeException {

    public RateNBServiceException(String message) {

        super(message);
    }
    public RateNBServiceException(String message, Throwable e) {

        super(message, e);
    }
}
