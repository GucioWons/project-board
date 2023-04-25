package com.example.notification.exception;

public class ApiException extends RuntimeException{
    public ApiException(String errorMessage){
        super(errorMessage);
    }
}
