package com.returdev.authentication_service.services.auth;

import com.returdev.authentication_service.model.UserTokens;

/**
 * Interface for authentication services.
 * This interface defines methods for handling user authentication,
 * including login, logout, and token management.
 */
public interface AuthService {

    /**
     * Authenticates a user and generates tokens.
     * This method validates the user's credentials and returns
     * access and refresh tokens for authenticated sessions.
     *
     * @param email the email of the user
     * @param password the password of the user
     * @return a `UserTokens` object containing the access and refresh tokens
     */
    UserTokens login(String email, String password);

    /**
     * Logs out a user by invalidating the refresh token.
     * This method ensures the user's session is terminated securely.
     *
     * @param refreshToken the refresh token to be invalidated
     */
    void logout(String refreshToken);

    /**
     * Provides a new access token using a valid refresh token.
     * This method generates a fresh access token for continued authentication.
     *
     * @param refreshToken the refresh token used to generate a new access token
     * @return a new access token as a `String`
     */
    String provideNewAccessToken(String refreshToken);

}
