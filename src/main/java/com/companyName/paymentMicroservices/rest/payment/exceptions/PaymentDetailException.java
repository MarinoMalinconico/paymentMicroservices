package com.companyName.paymentMicroservices.rest.payment.exceptions;

public class PaymentDetailException extends Exception {
    public PaymentDetailException(String message) {
        super(message);
    }
    public PaymentDetailException(String message, Throwable cause) {
        super(message, cause);
    }
}
