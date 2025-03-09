package com.returdev.catalog_service.dtos.equipment;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Data Transfer Object (DTO) for equipment responses.
 * This record is used to transfer equipment data from the server to the client.
 */
public record EquipmentResponseDTO(
        Long id,
        String name,
        @JsonProperty(namespace = "image_url")
        String imageUrl
) {}
