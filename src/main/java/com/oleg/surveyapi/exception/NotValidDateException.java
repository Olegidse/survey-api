package com.oleg.surveyapi.exception;

public class NotValidDateException extends RuntimeException {
    public NotValidDateException(String message) {
        super(message);
    }
}
