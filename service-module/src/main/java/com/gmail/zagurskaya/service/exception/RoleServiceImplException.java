package com.gmail.zagurskaya.service.exception;

public class RoleServiceImplException extends RuntimeException {

    public RoleServiceImplException(String message) {

        super(message);
    }
    public RoleServiceImplException(String message, Throwable e) {

        super(message, e);
    }
}
