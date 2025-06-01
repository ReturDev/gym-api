package com.returdev.authentication_service.services.token;

import com.returdev.authentication_service.model.UserDetails;

import java.time.Duration;

/**
 * Service interface for managing JWT tokens.
 * Provides methods for generating access and refresh tokens, as well as constants for token expiration times.
 */
public interface JwtTokenService {

    Duration REFRESH_TOKEN_EXPIRATION_TIME = Duration.ofDays(60);
    Duration TOKEN_EXPIRATION_TIME = Duration.ofMinutes(15);

    /**
     * Generates a JWT access token for the given user.
     *
     * @param user the user details used to generate the token
     * @return the generated JWT access token
     */
    String generateToken(UserDetails user);

    /**
     * Generates a JWT refresh token for the given user.
     *
     * @param user the user details used to generate the refresh token
     * @return the generated JWT refresh token
     */
    String generateRefreshToken(UserDetails user);

}
