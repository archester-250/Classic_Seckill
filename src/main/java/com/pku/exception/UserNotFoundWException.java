package com.pku.exception;

public class UserNotFoundWException extends RuntimeException {
    public UserNotFoundWException() {
        super();
    }
    public UserNotFoundWException(String message) {
        super(message);
    }
}
