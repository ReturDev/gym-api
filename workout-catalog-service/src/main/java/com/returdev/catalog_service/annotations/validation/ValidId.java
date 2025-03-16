package com.returdev.catalog_service.annotations.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.lang.annotation.*;

/**
 * Custom annotation for validating IDs.
 * This annotation ensures that the ID is not null and has a minimum value of 1.
 */
@Documented
@Constraint(validatedBy = {})
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@NotNull(message = "{validation.id.not_null_required.message}")
@Min(value = 1, message = "{validation.id.min_value.message}")
public @interface ValidId {

    String message() default "";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
