package com.mfplatform.exception;

public class BusinessException extends BaseApplicationException {
    private static final long serialVersionUID = 1L;


    public BusinessException(String errorCode, String message) {
        super(errorCode, message);
    }
}