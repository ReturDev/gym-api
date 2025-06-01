package com.returdev.authentication_service.services.auth;

import com.returdev.authentication_service.model.UserTokens;

/**
 * Interface for authentication services.
 * Provides methods for user login and logout functionality.
 */
public interface AuthService {

    /**
     * Authenticates a user and generates tokens.
     *
     * @param email the email of the user
     * @param password the password of the user
     * @return a UserTokens object containing the access and refresh tokens
     */
    UserTokens login(String email, String password);

    /**
     * Logs out a user by invalidating their session or tokens.
     *
     * @param email the email of the user to log out
     */
    void logout(String email);

}
