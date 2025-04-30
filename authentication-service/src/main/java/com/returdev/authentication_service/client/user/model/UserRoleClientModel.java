package com.returdev.authentication_service.client.user.model;

import java.util.List;

/**
 * Represents a user role client model used for communication between services.
 * This class is immutable and uses the `record` format introduced in Java 16.
 *
 * @param id Unique identifier of the user role.
 * @param name Name of the user role.
 * @param permissions List of permissions associated with the user role.
 */
public record UserRoleClientModel(
        Long id,
        String name,
        List<String> permissions
) {}
