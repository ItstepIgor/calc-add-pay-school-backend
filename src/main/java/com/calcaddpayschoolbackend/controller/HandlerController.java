package com.calcaddpayschoolbackend.controller;

import com.calcaddpayschoolbackend.exception.MyException;
import com.calcaddpayschoolbackend.exception.TemplateResponseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class HandlerController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<TemplateResponseException> handleInvalidFormatException(Exception e) {
        return getResponseEntity("I'm teapot", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<TemplateResponseException> handleEntityNotFoundException(EntityNotFoundException e) {
        return getResponseEntity(e.getClass().getName(), e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MyException.class)
    public ResponseEntity<TemplateResponseException> handleMyException(EntityNotFoundException e) {
        return getResponseEntity(e.getClass().getName(), e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<TemplateResponseException> getResponseEntity(String error, String message, HttpStatus status) {
        return new ResponseEntity<>(new TemplateResponseException(status.value(), error, message), status);
    }
}
