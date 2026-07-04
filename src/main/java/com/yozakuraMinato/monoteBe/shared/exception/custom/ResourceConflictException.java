package com.yozakuraMinato.monoteBe.shared.exception.custom;

public class ResourceConflictException extends RuntimeException {
    public ResourceConflictException(String message) {
        super(message);
    }
}
