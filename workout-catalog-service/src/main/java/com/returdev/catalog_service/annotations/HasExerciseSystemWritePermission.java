package com.returdev.catalog_service.annotations;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom annotation to enforce the 'EXERCISE_SYSTEM_WRITE' authority.
 * This annotation can be applied to methods to ensure that the user has the required permission.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@PreAuthorize("hasAuthority('EXERCISE_SYSTEM_WRITE')")
public @interface HasExerciseSystemWritePermission {
}
