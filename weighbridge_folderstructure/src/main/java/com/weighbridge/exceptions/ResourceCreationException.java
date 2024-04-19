package com.weighbridge.exceptions;

public class ResourceCreationException extends RuntimeException{

    public ResourceCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}