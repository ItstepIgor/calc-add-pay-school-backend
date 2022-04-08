package com.calcaddpayschoolbackend.controller;

import com.calcaddpayschoolbackend.exception.*;
import com.calcaddpayschoolbackend.util.ExtractStringFromException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlerController {


    @ExceptionHandler(Exception.class)
    public ResponseEntity<TemplateResponseException> handleInvalidFormatException(Exception e) {
        return getResponseEntity(e.getClass().getName(), e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoSuchEntityException.class)
    public ResponseEntity<TemplateResponseException> handleMyException(NoSuchEntityException e) {
        return getResponseEntity(e.getClass().getName(), e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoCurrentCalcDateException.class)
    public ResponseEntity<TemplateResponseException> handleMyException(NoCurrentCalcDateException exception) {
        return getResponseEntity(exception.getClass().getName(), exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BalanceSumException.class)
    public ResponseEntity<TemplateResponseException> handleMyException(BalanceSumException exception) {
        return getResponseEntity(exception.getClass().getName(), exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PercentValueException.class)
    public ResponseEntity<TemplateResponseException> handleMyException(PercentValueException exception) {
        return getResponseEntity(exception.getClass().getName(), exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotDeleteException.class)
    public ResponseEntity<TemplateResponseException> handleMyException(NotDeleteException exception) {
        return getResponseEntity(exception.getClass().getName(), exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotTimeSheetDayException.class)
    public ResponseEntity<TemplateResponseException> handleMyException(NotTimeSheetDayException exception) {
        return getResponseEntity(exception.getClass().getName(), exception.getMessage(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(EntityExistsOnThisDateException.class)
    public ResponseEntity<TemplateResponseException> handleMyException(EntityExistsOnThisDateException exception) {
        return getResponseEntity(exception.getClass().getName(), exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<TemplateResponseException> handleMyException(DataIntegrityViolationException e) {
        return getResponseEntity(e.getClass().getName(), ExtractStringFromException.extractStringFromException(e
                .getCause().getCause().getLocalizedMessage()), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<TemplateResponseException> getResponseEntity(String error, String message, HttpStatus status) {
        System.out.println(message);
        return new ResponseEntity<>(new TemplateResponseException(status.value(), error, message), status);
    }
}
