package com.example.firstspringboot.exception;

public class AnswerQuantityMismatchException extends RuntimeException {
    public AnswerQuantityMismatchException(String message){
        super(message);
    }
}
