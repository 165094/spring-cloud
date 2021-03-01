package com.test.consumption.moudle.develop.exception;

public class DeveloperException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public DeveloperException(String message) {
        super(message);
    }

    public DeveloperException(String message, Throwable cause) {
        super(message, cause) ;
    }
}
