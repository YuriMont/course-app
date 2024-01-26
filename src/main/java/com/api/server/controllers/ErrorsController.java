package com.api.server.controllers;

import com.api.server.exceptions.CourseNotFoundException;
import com.api.server.exceptions.EmailOrPasswordNotMatchesException;
import com.api.server.exceptions.ErrorInWebClientHttpRequestException;
import com.api.server.exceptions.PasswordsDoNotMatchException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorsController {

    @ExceptionHandler(PasswordsDoNotMatchException.class)
    public ResponseEntity<Object> handlePasswordsDoNotMatchException(PasswordsDoNotMatchException e){
        return ResponseEntity.status(404).body(e.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException e){
        return ResponseEntity.status(404).body("Email ou CPF já está sendo usado!");
    }

    @ExceptionHandler(EmailOrPasswordNotMatchesException.class)
    public ResponseEntity<Object> handleEmailOrPasswordNotMatchesException(EmailOrPasswordNotMatchesException e){
        return ResponseEntity.status(404).body(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        return ResponseEntity.status(404).body(e.getMessage());
    }

    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<Object> handleCourseNotFoundException(CourseNotFoundException e){
        return ResponseEntity.status(404).body(e.getMessage());
    }

    @ExceptionHandler(ErrorInWebClientHttpRequestException.class)
    public ResponseEntity<Object> handleErrorInWebClientHttpRequestException(ErrorInWebClientHttpRequestException e){
        return ResponseEntity.status(404).body(e.getMessage());
    }
}
