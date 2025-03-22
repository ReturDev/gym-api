package com.returdev.catalog_service.dtos.muscle;

import com.returdev.catalog_service.enums.MuscularGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object (DTO) for MuscleEntity.
 * This record is used to transfer muscle data between different layers of the application.
 */
public record MuscleRequestDTO(
        Long id,
        @Size(min = 3, max = 25) @NotBlank String name,
        @NotNull MuscularGroup muscularGroup
) {}
