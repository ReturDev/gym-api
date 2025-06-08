package com.returdev.authentication_service.client.user;

import com.returdev.authentication_service.client.user.model.UserClientModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * Feign client interface for interacting with the user service in the production environment.
 * This interface extends {@link UserFeignClient} and is annotated with `@Profile("prod")`
 * to indicate that it should only be used in the "prod" profile.
 */
@FeignClient(name = "${feign.client.user.name}", url = "${feign.client.user.url}")
@Profile("prod")
public interface UserFeignClientProd extends UserFeignClient {

    /**
     * Retrieves a user by their email address.
     * This method is mapped to the endpoint defined by the `feign.client.user.paths.getUserByEmail` property.
     *
     * @param email the email address of the user to retrieve
     * @return a {@link UserClientModel} representing the user details
     */
    @GetMapping("${feign.client.user.paths.getUserByEmail}")
    @Override
    UserClientModel getUserByEmail(String email);

    /**
     * Saves a refresh token for a user.
     * This method is mapped to the endpoint defined by the `feign.client.user.paths.saveRefreshToken` property.
     *
     * @param token the refresh token to save
     */
    @PutMapping("${feign.client.user.paths.saveRefreshToken}")
    @Override
    void saveRefreshToken(String email, String token);

}