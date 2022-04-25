package com.example.demo.exceptions;

public class HomTrainingException extends RuntimeException {
    public HomTrainingException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public HomTrainingException(String exMessage) {
        super(exMessage);
    }
}

