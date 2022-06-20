package com.example.firstspringboot.globalAdvice;

import com.example.firstspringboot.exception.AnswerNotFoundException;
import com.example.firstspringboot.exception.LevelNotFoundException;
import com.example.firstspringboot.exception.ProfileNotFoundException;
import com.example.firstspringboot.exception.QuestionNotFoundException;
import com.example.firstspringboot.payload.response.MessageError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<MessageError> exceptionHandler(AnswerNotFoundException exception){
        MessageError messageError = new MessageError();
        messageError.setMessage(exception.getMessage());
        return ResponseEntity.badRequest().body(messageError);
    }

    @ExceptionHandler
    public ResponseEntity<MessageError> exceptionHandler(LevelNotFoundException exception){
        MessageError messageError = new MessageError();
        messageError.setMessage(exception.getMessage());
        return ResponseEntity.badRequest().body(messageError);
    }

    @ExceptionHandler
    public ResponseEntity<MessageError> exceptionHandler(ProfileNotFoundException exception){
        MessageError messageError = new MessageError();
        messageError.setMessage(exception.getMessage());
        return ResponseEntity.badRequest().body(messageError);
    }

    @ExceptionHandler
    public ResponseEntity<MessageError> exceptionHandler(QuestionNotFoundException exception){
        MessageError messageError = new MessageError();
        messageError.setMessage(exception.getMessage());
        return ResponseEntity.badRequest().body(messageError);
    }

}
