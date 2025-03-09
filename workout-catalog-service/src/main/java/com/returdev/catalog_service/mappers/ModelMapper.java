package com.returdev.catalog_service.mappers;

/**
 * Interface for mapping between different model types.
 *
 * @param <R> the response DTO type
 * @param <S> the request DTO type
 * @param <E> the entity type
 */
public interface ModelMapper<R, S, E> {

    /**
     * Converts an entity to a response DTO.
     *
     * @param entity the entity to convert
     * @return the response DTO
     * @throws IllegalArgumentException if the entity is invalid or cannot be mapped
     */
    R toResponseDto(E entity);

    /**
     * Converts a request DTO to an entity.
     *
     * @param requestDto the request DTO to convert
     * @return the entity
     * @throws IllegalArgumentException if the request DTO is invalid or cannot be mapped
     */
    E toEntity(S requestDto);

}
