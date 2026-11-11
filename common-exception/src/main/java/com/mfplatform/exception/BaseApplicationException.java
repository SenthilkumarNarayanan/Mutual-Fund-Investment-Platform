package com.mfplatform.exception;

public class BaseApplicationException extends RuntimeException {

    private final String errorCode;
    private static final long serialVersionUID = 1L;
    public BaseApplicationException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}