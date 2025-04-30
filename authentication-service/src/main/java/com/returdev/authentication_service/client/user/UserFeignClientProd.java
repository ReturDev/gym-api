package com.returdev.authentication_service.client.user;

import com.returdev.authentication_service.client.user.model.UserClientModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;

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
     * Maps to a GET request to the endpoint specified by the `feign.client.user.getUserByEmail.url` property.
     *
     * @param email The email address of the user to retrieve.
     * @return A {@link UserClientModel} representing the user.
     */
    @GetMapping("${feign.client.user.getUserByEmail.url}")
    @Override
    UserClientModel getUserByEmail(String email);
}
