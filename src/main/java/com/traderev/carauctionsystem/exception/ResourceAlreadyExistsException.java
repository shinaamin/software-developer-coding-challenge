package com.traderev.carauctionsystem.exception;

/**
 * @author SHINA.
 * @version 1.0
 * @since 2018-08-10
 */
public class ResourceAlreadyExistsException extends RuntimeException {

    private String resourceId;

    public ResourceAlreadyExistsException(String resourceId, String message) {
        super(message);
        this.resourceId = resourceId;
    }
}
