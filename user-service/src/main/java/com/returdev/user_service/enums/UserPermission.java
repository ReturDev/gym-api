package com.returdev.user_service.enums;

/**
 * Enum representing the various permissions available in the application.
 * These permissions are used to control access to different features and operations
 * across the services.
 */
public enum UserPermission {

    // Permissions related to the User Service
    USER_ACCOUNT_BASICS,
    DELETE_USERS,
    MANAGE_USERS,
    MANAGE_USER_ROLES,

    // Permissions related to the Workout Catalog Service
    EXERCISE_SYSTEM_READ,
    EXERCISE_SYSTEM_WRITE;

}
