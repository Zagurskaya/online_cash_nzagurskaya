package com.gmail.zagurskaya.online.cash.repository.exception;

public class DatabaseConnectionException extends RuntimeException {

    public DatabaseConnectionException(String message, Throwable e) {

        super(message, e);
    }
}
