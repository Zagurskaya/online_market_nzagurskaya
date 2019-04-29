package com.gmail.zagurskaya.repository.exception;

public class DatabaseConnectionException extends RuntimeException {
    public DatabaseConnectionException(String message) {

        super(message);
    }

    public DatabaseConnectionException(String message, Throwable e) {

        super(message, e);
    }
}
