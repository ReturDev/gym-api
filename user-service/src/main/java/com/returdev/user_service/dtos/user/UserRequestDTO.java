package com.returdev.user_service.dtos.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.returdev.user_service.annotations.validations.ValidPassword;
import com.returdev.utils_library.annotations.ValidId;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;


/**
 * Data Transfer Object (DTO) for creating or updating a user in the system.
 * This record is used to transfer user data between different layers of the application.
 *
 * @param id the unique identifier of the user
 * @param username the username of the user, must be between 3 and 50 characters
 * @param name the first name of the user, must be between 3 and 50 characters
 * @param surnames the surnames of the user, with a maximum of 50 characters
 * @param email the email address of the user, must be a valid email format
 * @param password the password of the user, validated with custom password constraints
 * @param userRoleId the ID of the user role, validated with custom ID constraints
 */
public record UserRequestDTO(
        UUID id,
        @NotBlank @Size(min = 3, max = 50) String username,
        @NotBlank @Size(min = 3, max = 50) String name,
        @Size(max = 50) String surnames,
        @NotNull @Email String email,
        @ValidPassword String password,
        @JsonProperty("user_role_id") @ValidId Long userRoleId
) {
}
