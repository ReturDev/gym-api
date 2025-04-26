package com.returdev.user_service.dtos.password;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Data Transfer Object (DTO) for changing a user's password.
 * This record encapsulates the old and new passwords required for a password change request.
 *
 * @param oldPassword the current password of the user
 * @param newPassword the new password to be set for the user
 */
public record ChangePasswordRequestDTO(
        @JsonProperty("old_password") String oldPassword,
        @JsonProperty("new_password") String newPassword
) {}
