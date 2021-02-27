package com.sfl.cafemanager.service.exception;

public class ExistingOrderException extends RuntimeException {
    public ExistingOrderException() {
        super("The table has an existing open order");
    }
}
