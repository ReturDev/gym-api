package com.returdev.user_service.annotations.validations.validators;

import com.returdev.user_service.annotations.validations.ValidPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

/**
 * Validator class for validating passwords based on a specific pattern.
 * Implements the `ConstraintValidator` interface to provide custom validation logic.
 */
public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

    /**
     * Regular expression pattern for validating passwords.
     * The password must:
     * - Contain at least one digit.
     * - Contain at least one lowercase letter.
     * - Contain at least one uppercase letter.
     * - Contain at least one special character.
     * - Have no whitespace.
     * - Be at least 8 characters long.
     */
    private static final String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-={};':.,\\\\])(?=\\S+$).{8,}$";

    /**
     * Compiled `Pattern` object for the password regular expression.
     */
    private final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    /**
     * Validates the given password against the defined pattern.
     *
     * @param password the password to validate
     * @param context the context in which the constraint is evaluated
     * @return `true` if the password is valid, `false` otherwise
     */
    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        return password != null && pattern.matcher(password).matches();
    }
}
