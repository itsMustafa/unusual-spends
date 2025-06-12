package com.scw.ddd.unusual.spends.exception;

public class InvalidCreditCardNumberException extends RuntimeException {

    public InvalidCreditCardNumberException(String number) {
        super("Invalid Credit Card Number: " + number);
    }
}
