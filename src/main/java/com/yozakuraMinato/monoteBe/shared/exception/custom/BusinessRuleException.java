package com.yozakuraMinato.monoteBe.shared.exception.custom;

public class BusinessRuleException extends RuntimeException {
    public BusinessRuleException(String message) {
        super(message);
    }
}
