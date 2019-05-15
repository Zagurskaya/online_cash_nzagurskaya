package com.gmail.zagurskaya.online.cash.service.exception;

public class ReviewsServiceImplException extends RuntimeException {

    public ReviewsServiceImplException(String message) {

        super(message);
    }
    public ReviewsServiceImplException(String message, Throwable e) {

        super(message, e);
    }
}
