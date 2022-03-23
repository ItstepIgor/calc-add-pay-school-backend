package com.calcaddpayschoolbackend.exception;

public class EntityExistsOnThisDateException extends RuntimeException {
    public EntityExistsOnThisDateException(String message) {
        super(message);
    }
}
