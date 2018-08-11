package com.traderev.carauctionsystem.exception;

/**
 * @author SHINA.
 * @version 1.0
 * @since 2018-08-10
 */
public class ResourceNotFoundException extends RuntimeException {

    private String resourceId;

    public ResourceNotFoundException(String resourceId, String message) {
        super(message);
        this.resourceId = resourceId;
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}