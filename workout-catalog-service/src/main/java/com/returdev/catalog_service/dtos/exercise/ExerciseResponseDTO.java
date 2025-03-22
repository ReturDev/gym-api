package com.returdev.catalog_service.dtos.exercise;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.returdev.catalog_service.dtos.equipment.EquipmentRequestDTO;
import com.returdev.catalog_service.dtos.equipment.EquipmentResponseDTO;
import com.returdev.catalog_service.dtos.muscle.involved.MuscleInvolvedResponseDTO;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * Data Transfer Object (DTO) for ExerciseEntity.
 * This record is used to transfer exercise data between different layers of the application.
 *
 * @param id the unique identifier of the exercise
 * @param name the name of the exercise
 * @param description the description of the exercise
 * @param benchRequired indicates if a bench is required for the exercise
 * @param musclesInvolved the list of muscles involved in the exercise
 * @param equipment the equipment required for the exercise
 * @param imageUrl the URL of the image representing the exercise
 * @param videoUrl the URL of the video demonstrating the exercise
 * @param createdAt the date when the exercise was created
 * @param isVisible indicates if the exercise is visible
 */
public record ExerciseResponseDTO(
        Long id,
        String name,
        String description,
        @JsonProperty("is_bench_required") boolean benchRequired,
        @JsonProperty("muscles_involved") List<MuscleInvolvedResponseDTO> musclesInvolved,
        EquipmentResponseDTO equipment,
        @JsonProperty("image_url") String imageUrl,
        @JsonProperty("video_url") String videoUrl,
        @JsonProperty("created_at") LocalDateTime createdAt,
        @JsonProperty("is_visible") boolean isVisible
) {}
