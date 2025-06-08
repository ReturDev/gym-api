package com.returdev.authentication_service.services.auth;

import com.returdev.authentication_service.client.user.UserFeignClient;
import com.returdev.authentication_service.client.user.model.UserClientModel;
import com.returdev.authentication_service.exceptions.RefreshTokenValidationException;
import com.returdev.authentication_service.mapper.user.UserMapper;
import com.returdev.authentication_service.model.UserDetails;
import com.returdev.authentication_service.model.UserTokens;
import com.returdev.authentication_service.services.token.JwtTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * Implementation of the `AuthService` interface.
 * This class provides authentication-related functionalities such as login, logout, and token management.
 * It is annotated with `@Service` to indicate that it is a Spring-managed service component.
 * The `@RequiredArgsConstructor` annotation is used to generate a constructor for all final fields.
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {


    private final AuthenticationManager authManager;
    private final JwtTokenService tokenService;
    private final UserFeignClient userFeignClient;
    private final UserMapper userMapper;

    /**
     * Authenticates a user and generates access and refresh tokens.
     * If the user's refresh token is invalid, a new refresh token is generated and saved.
     *
     * @param email the email of the user
     * @param password the password of the user
     * @return a `UserTokens` object containing the access and refresh tokens
     */
    @Override
    public UserTokens login(String email, String password) {

        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email,
                        password
                )
        );

        UserDetails user = (UserDetails) authentication.getPrincipal();
        String finalRefreshToken;

        try {
            tokenService.getEmailIfRefreshTokenIsValid(user.getRefreshToken());
            finalRefreshToken = user.getRefreshToken();
        } catch (RefreshTokenValidationException ex) {
            finalRefreshToken = tokenService.generateRefreshToken(user);
            userFeignClient.saveRefreshToken(user.getUsername(), finalRefreshToken);
        }

        return new UserTokens(
                tokenService.generateToken(user),
                finalRefreshToken
        );
    }

    /**
     * Logs out a user by invalidating their refresh token.
     * Ensures the refresh token matches the one stored in the user service.
     *
     * @param refreshToken the refresh token to be invalidated
     * @throws RefreshTokenValidationException if the refresh token is invalid
     */
    @Override
    public void logout(String refreshToken) {

        String email = tokenService.getEmailIfRefreshTokenIsValid(refreshToken);

        if (!userFeignClient.getUserByEmail(email).refreshToken().equals(refreshToken)) {
            throw new RefreshTokenValidationException();
        }

        userFeignClient.saveRefreshToken(email, null);
    }

    /**
     * Provides a new access token using a valid refresh token.
     * Retrieves the user details from the user service and generates a new access token.
     *
     * @param refreshToken the refresh token used to generate a new access token
     * @return a new access token as a `String`
     */
    @Override
    public String provideNewAccessToken(String refreshToken) {

        String email = tokenService.getEmailIfRefreshTokenIsValid(refreshToken);

        UserClientModel user = userFeignClient.getUserByEmail(email);

        return tokenService.generateToken(
                userMapper.userClientModelToUserDetails(user)
        );
    }
}
