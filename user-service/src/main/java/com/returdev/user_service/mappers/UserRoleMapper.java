package com.returdev.user_service.mappers;

import com.returdev.user_service.dtos.role.UserRoleRequestDTO;
import com.returdev.user_service.dtos.role.UserRoleResponseDTO;
import com.returdev.user_service.enities.UserPermissionEntity;
import com.returdev.user_service.enities.UserRoleEntity;
import com.returdev.utils_library.mappers.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * Mapper class for converting between UserRoleEntity, UserRoleRequestDTO, and UserRoleResponseDTO.
 * This class is responsible for mapping user role-related data between different layers of the application.
 */
@Component
public class UserRoleMapper implements ModelMapper<UserRoleResponseDTO, UserRoleRequestDTO, UserRoleEntity> {

    /**
     * Maps a UserRoleEntity to a UserRoleResponseDTO.
     *
     * @param entity the UserRoleEntity to map
     * @return the mapped UserRoleResponseDTO
     */
    @Override
    public UserRoleResponseDTO mapToResponseDto(UserRoleEntity entity) {
        return new UserRoleResponseDTO(
                entity.getId(),
                entity.getRoleName(),
                entity.getPermissions().stream().map(UserPermissionEntity::getPermission).toList()
        );
    }

    /**
     * Maps a UserRoleRequestDTO to a UserRoleEntity.
     *
     * @param requestDto the UserRoleRequestDTO to map
     * @return the mapped UserRoleEntity
     */
    @Override
    public UserRoleEntity mapToEntity(UserRoleRequestDTO requestDto) {
        return new UserRoleEntity(
                requestDto.id(),
                requestDto.name(),
                requestDto.permissionIds().stream().map(id -> {
                    UserPermissionEntity permission = new UserPermissionEntity();
                    permission.setId(id);
                    return permission;
                }).toList()
        );
    }

    /**
     * Verifies and returns the valid order by field for user role entities.
     *
     * @param orderBy the order by field to verify
     * @return an empty string (to be implemented)
     */
    @Override
    public String verifyOrderBy(String orderBy) {
        return "";
    }
}
