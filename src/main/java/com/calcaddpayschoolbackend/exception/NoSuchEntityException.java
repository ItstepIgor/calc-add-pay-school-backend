package com.calcaddpayschoolbackend.exception;

public class NoSuchEntityException extends RuntimeException {
//    public MyException() {
//        super("Нет такой роли");
//    }

    public NoSuchEntityException(String string) {
        super(string);
    }
}
