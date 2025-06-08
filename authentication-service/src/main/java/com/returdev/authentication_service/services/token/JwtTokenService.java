package com.returdev.authentication_service.services.token;

import com.returdev.authentication_service.model.UserDetails;
import jakarta.validation.constraints.NotBlank;

import java.time.Duration;

/**
 * Service interface for managing JWT tokens.
 * This interface defines methods for generating access and refresh tokens,
 * validating refresh tokens, and provides constants for token expiration times.
 */
public interface JwtTokenService {

    Duration REFRESH_TOKEN_EXPIRATION_TIME = Duration.ofDays(60);
    Duration TOKEN_EXPIRATION_TIME = Duration.ofMinutes(15);

    /**
     * Generates a JWT access token for the given user.
     * This token is used for authenticating user requests within the application.
     *
     * @param user the user details used to generate the token
     * @return the generated JWT access token as a `String`
     */
    String generateToken(UserDetails user);

    /**
     * Generates a JWT refresh token for the given user.
     * This token is used to obtain new access tokens when the current access token expires.
     *
     * @param user the user details used to generate the refresh token
     * @return the generated JWT refresh token as a `String`
     */
    String generateRefreshToken(UserDetails user);

    /**
     * Validates a refresh token and retrieves the associated email address.
     * This method checks if the provided refresh token is valid and returns the email of the user.
     *
     * @param refreshToken the refresh token to validate
     * @return the email address associated with the valid refresh token as a `String`
     */
    String getEmailIfRefreshTokenIsValid(String refreshToken);

}
