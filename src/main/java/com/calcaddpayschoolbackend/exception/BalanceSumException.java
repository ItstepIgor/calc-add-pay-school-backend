package com.calcaddpayschoolbackend.exception;

public class BalanceSumException extends RuntimeException {
    public BalanceSumException() {
        super("Сумма списания выше разрешенной");
    }
}
