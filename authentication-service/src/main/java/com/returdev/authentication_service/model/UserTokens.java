package com.returdev.authentication_service.model;

/**
 * A record representing user tokens.
 * This record is used to encapsulate the access and refresh tokens for a user.
 *
 * @param accessToken the access token for the user
 * @param refreshToken the refresh token for the user
 */
public record UserTokens(
    String accessToken,
    String refreshToken
) {}
