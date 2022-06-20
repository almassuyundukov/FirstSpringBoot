package com.example.firstspringboot.exception;

public class LevelNotFoundException extends RuntimeException{
    public LevelNotFoundException(String message){
        super(message);
    }
}
