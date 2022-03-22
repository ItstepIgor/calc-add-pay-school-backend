package com.calcaddpayschoolbackend.exception;

public class NoCurrentCalcDateException extends RuntimeException {


    public NoCurrentCalcDateException() {
        super("Нет даты текущего расчета");
    }
}
