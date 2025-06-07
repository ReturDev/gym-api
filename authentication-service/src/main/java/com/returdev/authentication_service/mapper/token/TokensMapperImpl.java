package com.returdev.authentication_service.mapper.token;

import com.returdev.authentication_service.dto.token.UserTokensResponseDTO;
import com.returdev.authentication_service.model.UserTokens;
import org.springframework.stereotype.Component;

/**
 * Implementation of the TokensMapper interface.
 * This class provides the functionality to map user tokens to their corresponding response DTO.
 * It is annotated with `@Component` to indicate that it is a Spring-managed component.
 */
@Component
public class TokensMapperImpl implements TokensMapper {

    /**
     * Maps a `UserTokens` object to a `UserTokensResponseDTO`.
     * This method transforms the user tokens model into a format suitable for API responses.
     *
     * @param tokens the `UserTokens` object containing the user's access and refresh tokens
     * @return a `UserTokensResponseDTO` containing the mapped token data
     */
    @Override
    public UserTokensResponseDTO userTokensToUserTokensResponseDTO(UserTokens tokens) {
        return new UserTokensResponseDTO(tokens.accessToken(), tokens.refreshToken());
    }

}
