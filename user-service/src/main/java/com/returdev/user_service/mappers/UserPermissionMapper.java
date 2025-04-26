package com.returdev.user_service.mappers;

import com.returdev.user_service.dtos.permission.UserPermissionResponseDTO;
import com.returdev.user_service.entities.UserPermissionEntity;
import com.returdev.utils_library.mappers.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * Mapper class for converting between UserPermissionEntity and UserPermissionResponseDTO.
 * This class is responsible for mapping user permission-related data between different layers of the application.
 */
@Component
public class UserPermissionMapper implements ModelMapper<UserPermissionResponseDTO, Object, UserPermissionEntity> {

    /**
     * Maps a UserPermissionEntity to a UserPermissionResponseDTO.
     *
     * @param entity the UserPermissionEntity to map
     * @return the mapped UserPermissionResponseDTO
     */
    @Override
    public UserPermissionResponseDTO mapToResponseDto(UserPermissionEntity entity) {
        return new UserPermissionResponseDTO(
                entity.getId(),
                entity.getPermission()
        );
    }

    /**
     * Maps a request DTO to a UserPermissionEntity.
     * This method is not implemented and currently returns null.
     *
     * @param requestDto the request DTO to map
     * @return null (not implemented)
     */
    @Override
    public UserPermissionEntity mapToEntity(Object requestDto) {
        return null;
    }

    /**
     * Verifies and returns the valid order by field for user permission entities.
     * This method is not implemented and currently returns an empty string.
     *
     * @param orderBy the order by field to verify
     * @return an empty string (not implemented)
     */
    @Override
    public String verifyOrderBy(String orderBy) {
        return "";
    }
}
