package com.returdev.catalog_service.mappers;

import com.returdev.catalog_service.dtos.muscle.MuscleRequestDTO;
import com.returdev.catalog_service.dtos.muscle.MuscleResponseDTO;
import com.returdev.catalog_service.entities.MuscleEntity;
import com.returdev.utils_library.mappers.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * Mapper class for converting between MuscleEntity and its DTO representations.
 * This class implements the ModelMapper interface to provide mapping methods.
 */
@Component
public class MuscleMapper implements ModelMapper<MuscleResponseDTO, MuscleRequestDTO, MuscleEntity> {

    /**
     * Converts a MuscleEntity to a MuscleResponseDTO.
     *
     * @param entity the MuscleEntity to convert
     * @return the converted MuscleResponseDTO
     */
    @Override
    public MuscleResponseDTO mapToResponseDto(MuscleEntity entity) {
        return new MuscleResponseDTO(
                entity.getId(),
                entity.getName(),
                entity.getMuscularGroup()
        );
    }

    /**
     * Converts a MuscleRequestDTO to a MuscleEntity.
     *
     * @param requestDto the MuscleRequestDTO to convert
     * @return the converted MuscleEntity
     */
    @Override
    public MuscleEntity mapToEntity(MuscleRequestDTO requestDto) {
        return new MuscleEntity(
                requestDto.id(),
                requestDto.name(),
                requestDto.muscularGroup(),
                null
        );
    }

    @Override
    public String verifyOrderBy(String orderBy) {
        return "";
    }
}
