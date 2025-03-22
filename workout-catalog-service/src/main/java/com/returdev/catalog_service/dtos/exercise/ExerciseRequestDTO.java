package com.returdev.catalog_service.dtos.exercise;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.returdev.catalog_service.dtos.equipment.EquipmentRequestDTO;
import com.returdev.catalog_service.dtos.muscle.involved.MuscleInvolvedRequestDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

import java.util.List;

/**
 * Data Transfer Object (DTO) for ExerciseEntity.
 * This record is used to transfer exercise data between different layers of the application.
 */
public record ExerciseRequestDTO(
        Long id,
        @Size(min = 3, max = 50) @NotBlank String name,
        @NotNull String description,
        @JsonProperty("is_bench_required") @NotNull Boolean benchRequired,
        @JsonProperty("muscles_involved") @NotEmpty List<MuscleInvolvedRequestDTO> musclesInvolved,
        @NotNull EquipmentRequestDTO equipment,
        @JsonProperty("image_url") @URL @NotNull String imageUrl,
        @JsonProperty("video_url") @URL @NotNull String videoUrl,
        @JsonProperty("is_visible") @NotNull Boolean isVisible
) {}
