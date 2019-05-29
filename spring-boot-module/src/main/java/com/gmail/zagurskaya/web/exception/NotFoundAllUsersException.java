package com.gmail.zagurskaya.web.exception;

public class NotFoundAllUsersException extends RuntimeException {

    public NotFoundAllUsersException(String message) {

        super(message);
    }
    public NotFoundAllUsersException(String message, Throwable e) {

        super(message, e);
    }

}
