package com.returdev.catalog_service.dtos.content;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A generic content response DTO (Data Transfer Object) that encapsulates a single piece of content.
 *
 * @param <T> the type of the content in the response
 */
public record ContentResponseDTO<T>(
        @JsonProperty("data") T content
) {}
