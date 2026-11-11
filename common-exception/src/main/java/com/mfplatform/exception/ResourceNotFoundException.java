package com.mfplatform.exception;

public class ResourceNotFoundException extends BaseApplicationException {

    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String errorCode, String message) {
        super(errorCode, message);
    }
}