package com.gmail.zagurskaya.web.exception;

public class AccessDeniedException  extends RuntimeException {

    public AccessDeniedException(String message) {

        super(message);
    }

    public AccessDeniedException(String message, Throwable e) {

        super(message, e);
    }
}