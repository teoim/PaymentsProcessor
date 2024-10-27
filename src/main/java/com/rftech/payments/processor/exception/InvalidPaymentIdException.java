package com.rftech.payments.processor.exception;

public class InvalidPaymentIdException extends RuntimeException {

    public InvalidPaymentIdException(String message) {
        super(message);
    }

}
