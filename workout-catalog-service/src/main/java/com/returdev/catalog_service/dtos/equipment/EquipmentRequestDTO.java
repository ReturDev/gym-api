package com.returdev.catalog_service.dtos.equipment;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;


/**
 * Data Transfer Object (DTO) for equipment requests.
 * This record is used to transfer equipment data between client and server.
 */
public record EquipmentRequestDTO(
        Long id,
        @Size(min = 3, max = 25, message = "{validation.size.message}")
        @NotNull(message = "{validation.not_null_required.message")
        String name,
        @URL(message = "{validation.url.message}")
        @NotNull(message = "{validation.not_null_required.message")
        String imageUrl
) {}
