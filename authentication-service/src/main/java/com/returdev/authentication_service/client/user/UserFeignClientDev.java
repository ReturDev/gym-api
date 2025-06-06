package com.returdev.authentication_service.client.user;


import com.returdev.authentication_service.client.user.model.UserClientModel;
import com.returdev.authentication_service.client.user.model.UserRoleClientModel;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * Development implementation of the {@link UserFeignClient} interface.
 * This class is annotated with `@Profile("dev")` to indicate that it should
 * only be used in the "dev" profile. It provides a mock implementation
 * of the `getUserByEmail` method for development purposes.
 */
@Profile("dev")
@Component
@RequiredArgsConstructor
public class UserFeignClientDev implements UserFeignClient {

    private final PasswordEncoder passwordEncoder;

    /**
     * Mock implementation of the `getUserByEmail` method.
     * Returns a hardcoded {@link UserClientModel} instance for testing purposes.
     *
     * @param email The email address of the user to retrieve.
     * @return A {@link UserClientModel} instance with mock data.
     */
    @Override
    public UserClientModel getUserByEmail(String email) {
        return new UserClientModel(
                UUID.randomUUID(),
                "Username",
                "name",
                "surnames",
                "email@gmail.com",
                passwordEncoder.encode("1234"),
                new UserRoleClientModel(1L, "USER", List.of("Permission")),
                true,
                true
        );
    }

    @Override
    public void saveRefreshToken(String token) {
        System.out.println(token);
    }
}
