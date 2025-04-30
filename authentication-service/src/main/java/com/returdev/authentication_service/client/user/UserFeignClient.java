package com.returdev.authentication_service.client.user;


import com.returdev.authentication_service.client.user.model.UserClientModel;

/**
 * Feign client interface for interacting with the user service.
 * Provides methods for retrieving user information.
 */
public interface UserFeignClient {

    /**
     * Retrieves a user by their email address.
     *
     * @param email The email address of the user to retrieve.
     * @return A {@link UserClientModel} representing the user.
     */
    UserClientModel getUserByEmail(String email);

}
