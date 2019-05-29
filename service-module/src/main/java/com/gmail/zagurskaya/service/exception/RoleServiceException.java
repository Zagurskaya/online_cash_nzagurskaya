package com.gmail.zagurskaya.service.exception;

public class RoleServiceException extends RuntimeException {

    public RoleServiceException(String message) {

        super(message);
    }
    public RoleServiceException(String message, Throwable e) {

        super(message, e);
    }
}
