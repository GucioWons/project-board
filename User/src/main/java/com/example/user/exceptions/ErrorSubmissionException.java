package com.example.user.exceptions;

public class ErrorSubmissionException extends RuntimeException{
    public ErrorSubmissionException(String warning){
        super(warning);
    }
}
