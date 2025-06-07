package com.returdev.authentication_service.dto.token;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Data Transfer Object (DTO) for user tokens response.
 * This record is used to encapsulate the access token and refresh token
 * returned by the authentication service.
 */
public record UserTokensResponseDTO(
        @JsonProperty("access-token") String accessToken,
        @JsonProperty("refresh-token") String refreshToken
) {}
