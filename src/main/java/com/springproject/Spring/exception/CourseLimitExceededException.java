package com.springproject.Spring.exception;

public class CourseLimitExceededException extends RuntimeException {
    public CourseLimitExceededException(String message) {
        super(message);
    }
}
