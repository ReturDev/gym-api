package com.returdev.catalog_service.dtos.equipment;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;


/**
 * Data Transfer Object (DTO) for equipment requests.
 * This record is used to transfer equipment data between client and server.
 */
public record EquipmentRequestDTO(
        Long id,
        @Size(min = 3, max = 25) @NotBlank String name,
        @JsonProperty("image_url") @URL @NotNull String imageUrl
) {}
