package com.calcaddpayschoolbackend.exception;

public class PercentValueException extends RuntimeException {
    public PercentValueException() {
        super("Внесенный процент выше возможного");
    }
}
