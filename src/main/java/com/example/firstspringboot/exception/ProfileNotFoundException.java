package com.example.firstspringboot.exception;

public class ProfileNotFoundException extends RuntimeException{
    public ProfileNotFoundException (String message){
        super(message);
    }
}
