package com.returdev.user_service.dtos.permission;

import com.returdev.user_service.enums.UserPermission;

/**
 * Data Transfer Object (DTO) for representing a user's permission.
 * This record is used to transfer user permission data between different layers of the application.
 *
 * @param id the unique identifier of the user permission
 * @param permission the specific permission associated with the user
 */
public record UserPermissionResponseDTO(
        Long id,
        UserPermission permission
) {
}
