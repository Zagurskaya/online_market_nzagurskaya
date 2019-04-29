package com.gmail.zagurskaya.service.exception;

public class ServiceException extends RuntimeException {

    public ServiceException(String message) {

        super(message);
    }
    public ServiceException(String message, Throwable e) {

        super(message, e);
    }
}
