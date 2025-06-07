package com.returdev.authentication_service.dto.credentials;

/**
 * Data Transfer Object (DTO) for user credentials request.
 * This record is used to encapsulate the user's email and password
 * provided during authentication or login requests.
 */
public record UserCredentialsRequestDTO(
        String email,
        String password
) {}
