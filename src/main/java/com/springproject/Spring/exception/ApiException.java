package com.springproject.Spring.exception;

public class ApiException extends RuntimeException {
    public ApiException(String message) {
        super(message);
    }
}
