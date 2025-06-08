package com.returdev.authentication_service.client.user;


import com.returdev.authentication_service.client.user.model.UserClientModel;

/**
 * Feign client interface for interacting with the user service.
 * Provides methods for retrieving user information and saving refresh tokens.
 */
public interface UserFeignClient {

    /**
     * Retrieves a user by their email address.
     * This method is used to fetch user details from the user service.
     *
     * @param email The email address of the user to retrieve.
     * @return A {@link UserClientModel} representing the user.
     */
    UserClientModel getUserByEmail(String email);

    /**
     * Saves a refresh token for a user.
     * This method is used to store the refresh token in the user service.
     *
     * @param token The refresh token to save.
     */
    void saveRefreshToken(String email, String token);

}