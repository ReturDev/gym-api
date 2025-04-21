package com.returdev.user_service.dtos.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.returdev.user_service.dtos.role.UserRoleResponseDTO;

import java.util.UUID;


/**
 * Data Transfer Object (DTO) for representing a user in the system.
 * This record is used to transfer user data between different layers of the application.
 *
 * @param id the unique identifier of the user
 * @param username the username of the user
 * @param name the first name of the user
 * @param surnames the surnames of the user
 * @param email the email address of the user
 * @param password the password of the user (hashed or encrypted)
 * @param userRole the role assigned to the user, represented as a UserRoleResponseDTO
 * @param isVerified whether the user's email is verified
 * @param isEnabled whether the user's account is enabled
 */
public record UserResponseDTO(
    UUID id,
    String username,
    String name,
    String surnames,
    String email,
    String password,
    @JsonProperty("user_role") UserRoleResponseDTO userRole,
    @JsonProperty("is_verified") boolean isVerified,
    @JsonProperty("is_enabled") boolean isEnabled
) {}
