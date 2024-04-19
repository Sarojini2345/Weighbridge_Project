package com.weighbridge.exceptions;

public class ResourceRetrievalException extends RuntimeException {
    public ResourceRetrievalException(String message, Exception cause) {
        super(message, cause);
    }
}
