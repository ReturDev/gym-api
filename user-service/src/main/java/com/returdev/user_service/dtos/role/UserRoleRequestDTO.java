package com.returdev.user_service.dtos.role;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

/**
 * Data Transfer Object (DTO) for creating or updating a user role in the system.
 * This record is used to transfer user role data between different layers of the application.
 *
 * @param id the unique identifier of the user role
 * @param name the name of the user role, must be between 3 and 25 characters
 * @param permissionIds the list of permission IDs associated with the user role, must not be empty
 */
public record UserRoleRequestDTO(
        Long id,
        @NotBlank @Size(min = 3, max = 25) String name,
        @JsonProperty("permission_ids") @NotEmpty List<Long> permissionIds
) {}
