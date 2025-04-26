package com.returdev.user_service.dtos.role;

import com.returdev.user_service.enums.UserPermission;

import java.util.List;

/**
 * Data Transfer Object (DTO) for representing a user role.
 * This record is used to transfer data about a user role, including its ID, name, and associated permissions.
 *
 * @param id the unique identifier of the user role
 * @param name the name of the user role
 * @param permissions the list of permissions associated with the user role
 */
public record UserRoleResponseDTO(
        Long id,
        String name,
        List<UserPermission> permissions
) {
}