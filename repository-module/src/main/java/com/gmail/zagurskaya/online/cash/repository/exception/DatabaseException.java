package com.gmail.zagurskaya.online.cash.repository.exception;

public class DatabaseException extends RuntimeException {

    public DatabaseException(String message, Throwable e) {
        super(message, e);
    }
}
