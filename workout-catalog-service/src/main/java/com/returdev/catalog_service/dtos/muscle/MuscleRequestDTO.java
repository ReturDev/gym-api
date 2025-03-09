package com.returdev.catalog_service.dtos.muscle;

import com.returdev.catalog_service.enums.MuscularGroup;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object (DTO) for MuscleEntity.
 * This record is used to transfer muscle data between different layers of the application.
 */
public record MuscleRequestDTO(
        Long id,
        @Size(min = 3, max = 25, message = "{validation.size.message}")
        @NotNull(message = "{validation.not_null_required.message}")
        String name,
        @NotNull(message = "{validation.not_null_required.message}") MuscularGroup muscularGroup
) {}
