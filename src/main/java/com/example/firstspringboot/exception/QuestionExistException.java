package com.example.firstspringboot.exception;

public class QuestionExistException extends RuntimeException {
    public QuestionExistException(String message) {
        super(message);
    }
}
