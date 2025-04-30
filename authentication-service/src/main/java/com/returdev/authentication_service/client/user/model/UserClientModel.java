package com.returdev.authentication_service.client.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

/**
 * Represents a user client model used for communication between services.
 * This class is immutable and uses the `record` format introduced in Java 16.
 *
 * @param id Unique identifier of the user (UUID).
 * @param username Unique username of the user.
 * @param name First name of the user.
 * @param surnames Last name(s) of the user.
 * @param email Email address of the user.
 * @param password Password of the user.
 * @param userRole Role of the user represented by a user role client model.
 * @param isVerified Indicates whether the user's account is verified.
 * @param isEnabled Indicates whether the user's account is enabled.
 */
public record UserClientModel(
        UUID id,
        String username,
        String name,
        String surnames,
        String email,
        String password,
        @JsonProperty("user_role") UserRoleClientModel userRole,
        @JsonProperty("is_verified") boolean isVerified,
        @JsonProperty("is_enabled") boolean isEnabled
) {}