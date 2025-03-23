package com.returdev.catalog_service.dtos.muscle.involved;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.returdev.catalog_service.dtos.muscle.MuscleRequestDTO;
import com.returdev.catalog_service.enums.MuscleActivationLevel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 * Data Transfer Object (DTO) for representing a muscle involved in an exercise.
 * This record is used to transfer data about the muscle and its activation level.
 *
 * @param muscle the muscle involved in the exercise
 * @param activationLevel the level of activation of the muscle during the exercise
 */
public record MuscleInvolvedRequestDTO(
        @Valid @NotNull MuscleRequestDTO muscle,
        @NotNull @JsonProperty("activation_level") MuscleActivationLevel activationLevel
) {}
