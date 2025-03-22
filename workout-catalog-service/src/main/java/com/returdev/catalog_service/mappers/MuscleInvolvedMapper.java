package com.returdev.catalog_service.mappers;

import com.returdev.catalog_service.dtos.muscle.involved.MuscleInvolvedRequestDTO;
import com.returdev.catalog_service.dtos.muscle.involved.MuscleInvolvedResponseDTO;
import com.returdev.catalog_service.entities.MuscleInvolvedEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Mapper class for converting between MuscleInvolvedEntity and its DTO representations.
 * This class implements the ModelMapper interface to provide mapping methods.
 */
@Component
@AllArgsConstructor
public class MuscleInvolvedMapper implements ModelMapper<MuscleInvolvedResponseDTO, MuscleInvolvedRequestDTO, MuscleInvolvedEntity> {

    private final MuscleMapper muscleMapper;

    /**
     * Converts a MuscleInvolvedEntity to a MuscleInvolvedResponseDTO.
     *
     * @param entity the MuscleInvolvedEntity to convert
     * @return the converted MuscleInvolvedResponseDTO
     */
    @Override
    public MuscleInvolvedResponseDTO mapToResponseDto(MuscleInvolvedEntity entity) {
        return new MuscleInvolvedResponseDTO(
                entity.getId(),
                muscleMapper.mapToResponseDto(entity.getMuscle()),
                entity.getActivationLevel()
        );
    }

    /**
     * Converts a MuscleInvolvedRequestDTO to a MuscleInvolvedEntity.
     *
     * @param requestDto the MuscleInvolvedRequestDTO to convert
     * @return the converted MuscleInvolvedEntity
     */
    @Override
    public MuscleInvolvedEntity mapToEntity(MuscleInvolvedRequestDTO requestDto) {
        return new MuscleInvolvedEntity(
                null,
                muscleMapper.mapToEntity(requestDto.muscle()),
                requestDto.activationLevel()
        );
    }
}
