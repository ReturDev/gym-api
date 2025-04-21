package com.returdev.user_service.annotations.validations;

import com.returdev.user_service.annotations.validations.validators.PasswordValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * Custom annotation for validating passwords based on specific criteria.
 * This annotation is validated by the `PasswordValidator` class.
 */
@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.FIELD})
public @interface ValidPassword {

    String message() default "{validation.ValidPassword.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
