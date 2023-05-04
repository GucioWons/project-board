package com.example.user.exception;

public class CustomomizeException extends RuntimeException {
    public CustomomizeException(String warning){
        super(warning);
    }
}
