package com.gmail.zagurskaya.service.exception;

public class UserServiceImplException extends RuntimeException {

    public UserServiceImplException(String message) {

        super(message);
    }
    public UserServiceImplException(String message, Throwable e) {

        super(message, e);
    }
}
