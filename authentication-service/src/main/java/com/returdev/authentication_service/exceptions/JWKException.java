package com.returdev.authentication_service.exceptions;

/**
 * Custom exception class for handling JWK (JSON Web Key) related errors.
 * This class extends {@link RuntimeException} and provides multiple constructors
 * for different use cases.
 */
public class JWKException extends RuntimeException {

    /**
     * Default constructor for {@link JWKException}.
     * Creates an exception instance without any message or cause.
     */
    public JWKException() {}

    /**
     * Constructor for {@link JWKException} with a custom error message.
     *
     * @param message The detail message explaining the reason for the exception.
     */
    public JWKException(String message) {
        super(message);
    }

    /**
     * Constructor for {@link JWKException} with a custom error message and a cause.
     *
     * @param message The detail message explaining the reason for the exception.
     * @param cause   The underlying cause of the exception.
     */
    public JWKException(String message, Throwable cause) {
        super(message, cause);
    }

}
