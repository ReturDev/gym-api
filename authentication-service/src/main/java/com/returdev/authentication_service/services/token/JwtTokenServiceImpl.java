package com.returdev.authentication_service.services.token;

import com.returdev.authentication_service.exceptions.RefreshTokenValidationException;
import com.returdev.authentication_service.model.UserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

/**
 * Implementation of the `JwtTokenService` interface.
 * This class provides methods for generating JWT access and refresh tokens,
 * as well as validating refresh tokens. It is annotated with `@Service` to indicate
 * that it is a Spring-managed service component. The `@RequiredArgsConstructor` annotation
 * is used to generate a constructor for all final fields.
 */
@Service
@RequiredArgsConstructor
public class JwtTokenServiceImpl implements JwtTokenService {


    private final JwtEncoder encoder;
    private final JwtDecoder decoder;

    /**
     * Generates a JWT access token for the given user.
     * The token includes claims such as user ID, username, name, surnames, role, and permissions.
     *
     * @param user the user details used to generate the token
     * @return the generated JWT access token as a `String`
     */
    @Override
    public String generateToken(UserDetails user) {

        Instant now = Instant.now();
        String userRole = user.getAuthorities().stream()
                .filter(authority -> authority.getAuthority().contains("ROLE_"))
                .findFirst()
                .orElseThrow()
                .getAuthority();
        List<String> permissions = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .filter(authority -> !authority.contains("ROLE_"))
                .toList();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .claim("id", user.getId())
                .subject(user.getUsername())
                .claim("name", user.getName())
                .claim("surnames", user.getSurnames())
                .claim("role", userRole)
                .claim("permissions", permissions)
                .issuedAt(now)
                .expiresAt(now.plus(TOKEN_EXPIRATION_TIME))
                .build();

        JwtEncoderParameters parameters = JwtEncoderParameters.from(claims);

        return encoder.encode(parameters).getTokenValue();
    }

    /**
     * Generates a JWT refresh token for the given user.
     * The token includes claims such as the username, type, and a unique identifier (JTI).
     *
     * @param user the user details used to generate the refresh token
     * @return the generated JWT refresh token as a `String`
     */
    @Override
    public String generateRefreshToken(UserDetails user) {
        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .subject(user.getUsername())
                .issuedAt(now)
                .expiresAt(now.plus(REFRESH_TOKEN_EXPIRATION_TIME))
                .claim("type", "refresh")
                .claim("jti", UUID.randomUUID().toString())
                .build();

        JwtEncoderParameters parameters = JwtEncoderParameters.from(claims);

        return encoder.encode(parameters).getTokenValue();
    }

    /**
     * Validates a refresh token and retrieves the associated email address.
     * Throws a `RefreshTokenValidationException` if the token is invalid or not of type "refresh".
     *
     * @param refreshToken the refresh token to validate
     * @return the email address associated with the valid refresh token as a `String`
     * @throws RefreshTokenValidationException if the refresh token is invalid
     */
    @Override
    public String getEmailIfRefreshTokenIsValid(String refreshToken) {

        if (refreshToken == null || refreshToken.isBlank()) {
            throw new RefreshTokenValidationException();
        }

        Jwt jwt = decoder.decode(refreshToken);

        if (!jwt.getClaim("type").equals("refresh")) {
            throw new RefreshTokenValidationException();
        }

        return jwt.getSubject();
    }
}
