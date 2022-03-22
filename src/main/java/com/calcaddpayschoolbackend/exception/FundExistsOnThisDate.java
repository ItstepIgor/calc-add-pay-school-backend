package com.calcaddpayschoolbackend.exception;

public class FundExistsOnThisDate extends RuntimeException {
    public FundExistsOnThisDate(String message) {
        super(message);
    }
}
