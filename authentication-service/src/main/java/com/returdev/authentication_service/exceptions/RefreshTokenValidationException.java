package com.returdev.authentication_service.exceptions;

/**
 * Exception class for handling refresh token validation errors.
 * This class extends `RuntimeException` and provides constructors
 * for creating exceptions with custom messages and causes.
 */
public class RefreshTokenValidationException extends RuntimeException {

    /**
     * Constructs a new `RefreshTokenValidationException` with the specified detail message.
     *
     * @param message the detail message explaining the reason for the exception
     */
    public RefreshTokenValidationException(String message) {
        super(message);
    }

    /**
     * Constructs a new `RefreshTokenValidationException` with no detail message.
     */
    public RefreshTokenValidationException() {
    }

    /**
     * Constructs a new `RefreshTokenValidationException` with the specified detail message
     * and cause.
     *
     * @param message the detail message explaining the reason for the exception
     * @param cause the cause of the exception (can be null)
     */
    public RefreshTokenValidationException(String message, Throwable cause) {
        super(message, cause);
    }

}
