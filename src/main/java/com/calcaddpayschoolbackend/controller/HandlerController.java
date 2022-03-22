package com.calcaddpayschoolbackend.controller;

import com.calcaddpayschoolbackend.exception.NoCurrentCalcDateException;
import com.calcaddpayschoolbackend.exception.NoSuchEntityException;
import com.calcaddpayschoolbackend.exception.PercentValueException;
import com.calcaddpayschoolbackend.exception.TemplateResponseException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @ExceptionHandler(PercentValueException.class)
    public ResponseEntity<TemplateResponseException> handleMyException(PercentValueException exception) {
        return getResponseEntity(exception.getClass().getName(), exception.getMessage(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<TemplateResponseException> handleMyException(DataIntegrityViolationException e) {
        return getResponseEntity(e.getClass().getName(), extractStringException(e.getCause().getCause().getLocalizedMessage()), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<TemplateResponseException> getResponseEntity(String error, String message, HttpStatus status) {
        return new ResponseEntity<>(new TemplateResponseException(status.value(), error, message), status);
    }


    private String extractStringException(String string) {
        String str = null;
        String str1 = null;
        String regex = ("(Подробности:)(\\s+\\D*\\(id\\)\\D*\\d+\\D*?)(\"+)(\\D*)(\")");
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()) {
            str = matcher.group(2);
            str1 = matcher.group(4);
        }
        return str + findRuNameTable(str1);
    }

    private String findRuNameTable(String string) {
        Map<String, String> tables = Map.of("add_pay", "Дополнительные оплаты", "add_pay_fund", "Фонды",
                "add_pay_result", "Результаты расчета", "percent_salary_result", "Премиальные",
                "staff_list", "Штатное расписание", "time_sheet", "Табель", "users", "Пользователи");

        return tables.entrySet().stream()
                .filter(stringStringEntry -> stringStringEntry
                        .getKey()
                        .equals(string))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(" ");
    }
}
