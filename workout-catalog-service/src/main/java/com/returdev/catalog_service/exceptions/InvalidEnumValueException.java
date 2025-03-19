package com.returdev.catalog_service.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Exception thrown when an invalid enum value is encountered.
 */
@Getter
@AllArgsConstructor
public class InvalidEnumValueException extends RuntimeException {

    /**
     * The invalid value that caused the exception.
     */
    private final String invalidValue;

    /**
     * The message resource code associated with the exception.
     */
    private final String messageResource;

    /**
     * A string representation of valid values for the enum.
     */
    private final String validValues;

    /**
     * Returns the detail message string of this exception.
     *
     * @return the detail message string of this {@code InvalidEnumValueException} instance
     */
    @Override
    public String getMessage() {
        return String.format("Invalid value: '%s'. Expected one of: [%s]. Message resource code: %s",
                invalidValue, validValues, messageResource);
    }
}
