package com.calcaddpayschoolbackend.exception;

public class NotDeleteException extends RuntimeException {
    public NotDeleteException() {
        super("Администратора удалять запрещено");
    }
}
