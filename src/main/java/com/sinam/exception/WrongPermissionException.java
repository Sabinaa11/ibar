package com.sinam.exception;

public class WrongPermissionException extends RuntimeException {

    public WrongPermissionException(String message) {
        super(message);
    }
}