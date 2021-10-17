package com.aiecel.alphatesttask.exception;

public class UnknownCurrencyException extends RuntimeException {
    public UnknownCurrencyException() {
        super("Unknown currency symbol");
    }

    public UnknownCurrencyException(String currency) {
        super("Unknown currency symbol: " + currency);
    }
}
