package com.returdev.catalog_service.dtos.muscle;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.returdev.catalog_service.enums.MuscularGroup;

/**
 * Data Transfer Object (DTO) for MuscleEntity.
 * This record is used to transfer muscle data between different layers of the application.
 */
public record MuscleResponseDTO(
        Long id,
        String name,
        @JsonProperty("muscular_group")
        MuscularGroup muscularGroup
) {}
