package com.returdev.catalog_service.mappers;

import com.returdev.catalog_service.dtos.exercise.ExerciseRequestDTO;
import com.returdev.catalog_service.dtos.exercise.ExerciseResponseDTO;
import com.returdev.catalog_service.entities.ExerciseEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Mapper class for converting between ExerciseEntity and its DTO representations.
 * This class implements the ModelMapper interface to provide mapping methods.
 */
@Component
@AllArgsConstructor
public class ExerciseMapper implements ModelMapper<ExerciseResponseDTO, ExerciseRequestDTO, ExerciseEntity> {

    private final EquipmentMapper equipmentMapper;
    private final MuscleInvolvedMapper muscleInvolvedMapper;

    /**
     * Converts an ExerciseEntity to an ExerciseResponseDTO.
     *
     * @param entity the ExerciseEntity to convert
     * @return the converted ExerciseResponseDTO
     */
    @Override
    public ExerciseResponseDTO mapToResponseDto(ExerciseEntity entity) {
        return new ExerciseResponseDTO(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.isBenchRequired(),
                entity.getMusclesInvolved().stream().map(muscleInvolvedMapper::mapToResponseDto).toList(),
                equipmentMapper.mapToResponseDto(entity.getEquipment()),
                entity.getExerciseImageUrl(),
                entity.getExerciseVideoUrl(),
                entity.getCreatedAt(),
                entity.isVisible()
        );
    }

    /**
     * Converts an ExerciseRequestDTO to an ExerciseEntity.
     *
     * @param requestDto the ExerciseRequestDTO to convert
     * @return the converted ExerciseEntity
     */
    @Override
    public ExerciseEntity mapToEntity(ExerciseRequestDTO requestDto) {
        return new ExerciseEntity(
                requestDto.id(),
                requestDto.name(),
                requestDto.description(),
                requestDto.benchRequired(),
                requestDto.musclesInvolved().stream().map(muscleInvolvedMapper::mapToEntity).toList(),
                equipmentMapper.mapToEntity(requestDto.equipment()),
                requestDto.imageUrl(),
                requestDto.videoUrl(),
                null,
                requestDto.isVisible()
        );
    }

}
