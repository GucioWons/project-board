package com.example.notification.exception;

public class NoNotificationException extends ApiException{
    public NoNotificationException(String errorMessage) {
        super(errorMessage);
    }
}
