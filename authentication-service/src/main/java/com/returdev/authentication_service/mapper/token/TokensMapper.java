package com.returdev.authentication_service.mapper.token;

import com.returdev.authentication_service.dto.token.UserTokensResponseDTO;
import com.returdev.authentication_service.model.UserTokens;

/**
 * Mapper interface for converting user tokens to their corresponding response DTO.
 * This interface defines methods for mapping between the `UserTokens` model
 * and the `UserTokensResponseDTO` data transfer object.
 */
public interface TokensMapper {

    /**
     * Maps a `UserTokens` object to a `UserTokensResponseDTO`.
     * This method is used to transform the user tokens model into a format
     * suitable for API responses.
     *
     * @param tokens the `UserTokens` object containing the user's tokens
     * @return a `UserTokensResponseDTO` containing the mapped token data
     */
    UserTokensResponseDTO userTokensToUserTokensResponseDTO(UserTokens tokens);

}
