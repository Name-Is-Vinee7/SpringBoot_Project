package com.example.expenseTracker.exceptionHandling;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message,Throwable cause,boolean enableSuppression,boolean writableStackTrace) {
        super(message,cause,enableSuppression,writableStackTrace);

    }

}
