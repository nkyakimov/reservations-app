package com.app.reservation.exception;

public class NotFoundException extends ApplicationException {

    public NotFoundException() {
        super("Resource not found", 404);
    }

}
