package com.gmail.zagurskaya.online.cash.service.exception;

public class ReviewsServiceException extends RuntimeException {

    public ReviewsServiceException(String message) {

        super(message);
    }
    public ReviewsServiceException(String message, Throwable e) {

        super(message, e);
    }
    
}
