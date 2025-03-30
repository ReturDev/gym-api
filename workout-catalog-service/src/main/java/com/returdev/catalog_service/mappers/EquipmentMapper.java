package com.returdev.catalog_service.mappers;

import com.returdev.catalog_service.dtos.equipment.EquipmentRequestDTO;
import com.returdev.catalog_service.dtos.equipment.EquipmentResponseDTO;
import com.returdev.catalog_service.entities.EquipmentEntity;
import com.returdev.utils_library.mappers.ModelMapper;
import com.returdev.utils_library.utils.EnumUtil;
import org.springframework.stereotype.Component;

/**
 * Mapper implementation for converting between EquipmentEntity and its DTOs.
 */
@Component
public class EquipmentMapper implements ModelMapper<EquipmentResponseDTO, EquipmentRequestDTO, EquipmentEntity> {

    /**
     * Converts an EquipmentEntity to an EquipmentResponseDTO.
     *
     * @param entity the EquipmentEntity to convert
     * @return the converted EquipmentResponseDTO
     */
    @Override
    public EquipmentResponseDTO mapToResponseDto(EquipmentEntity entity) {
        return new EquipmentResponseDTO(
                entity.getId(),
                entity.getName(),
                entity.getImageUrl()
        );
    }

    /**
     * Converts an EquipmentRequestDTO to an EquipmentEntity.
     *
     * @param requestDto the EquipmentRequestDTO to convert
     * @return the converted EquipmentEntity
     */
    @Override
    public EquipmentEntity mapToEntity(EquipmentRequestDTO requestDto) {
        return new EquipmentEntity(
                requestDto.id(),
                requestDto.name(),
                requestDto.imageUrl()
        );
    }

    @Override
    public String verifyOrderBy(String orderBy) {
        return (orderBy != null ? EquipmentOrderBy.valueOf(orderBy) : EquipmentOrderBy.ID).entityPropertyName;
    }

    private enum EquipmentOrderBy {

        ID("id"),
        NAME("name");

        private final String entityPropertyName;

        EquipmentOrderBy(String entityPropertyName) {
            this.entityPropertyName = entityPropertyName;
        }

        public static EquipmentOrderBy fromString(String value) {
            return EnumUtil.fromString(EquipmentOrderBy.class, value);
        }

    }
}
