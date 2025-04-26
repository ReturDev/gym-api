package com.returdev.user_service.mappers;

import com.returdev.user_service.dtos.user.UserRequestDTO;
import com.returdev.user_service.dtos.user.UserResponseDTO;
import com.returdev.user_service.enities.UserEntity;
import com.returdev.user_service.enities.UserRoleEntity;
import com.returdev.utils_library.mappers.ModelMapper;
import com.returdev.utils_library.utils.EnumUtil;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Component;

/**
 * Mapper class for converting between UserEntity, UserRequestDTO, and UserResponseDTO.
 * This class is responsible for mapping user-related data between different layers of the application.
 */
@Component
@RequiredArgsConstructor
public class UserMapper implements ModelMapper<UserResponseDTO, UserRequestDTO, UserEntity> {

    private final UserRoleMapper userRoleMapper;

    /**
     * Maps a UserEntity to a UserResponseDTO.
     *
     * @param entity the UserEntity to map
     * @return the mapped UserResponseDTO
     */
    @Override
    public UserResponseDTO mapToResponseDto(UserEntity entity) {
        return new UserResponseDTO(
                entity.getId(),
                entity.getUsername(),
                entity.getName(),
                entity.getSurnames(),
                entity.getEmail(),
                entity.getPassword(),
                userRoleMapper.mapToResponseDto(entity.getUserRole()),
                entity.isVerified(),
                entity.isEnabled()
        );
    }

    /**
     * Maps a UserRequestDTO to a UserEntity.
     *
     * @param requestDto the UserRequestDTO to map
     * @return the mapped UserEntity
     */
    @Override
    public UserEntity mapToEntity(UserRequestDTO requestDto) {

        UserRoleEntity role = new UserRoleEntity();
        role.setId(requestDto.userRoleId());

        return new UserEntity(
                requestDto.id(),
                requestDto.username(),
                requestDto.name(),
                requestDto.username(),
                requestDto.email(),
                requestDto.password(),
                role
        );
    }

    /**
     * Verifies and returns the valid order by field for user entities.
     *
     * @param orderBy the order by field to verify
     * @return the verified order by field
     */
    @Override
    public String verifyOrderBy(String orderBy) {
        return (orderBy != null ? UserOrderBy.fromString(orderBy) : UserOrderBy.ID).entityPropertyName;
    }

    /**
     * Enum representing the possible fields to order user entities by.
     */
    @RequiredArgsConstructor
    private enum UserOrderBy{

        ID("id"),
        NAME("name"),
        EMAIL("email");

        private final String entityPropertyName;

        /**
         * Converts a string value to a UserOrderBy enum.
         *
         * @param value the string value to convert
         * @return the corresponding UserOrderBy enum
         */
        public static UserOrderBy fromString(String value){
            return EnumUtil.fromString(UserOrderBy.class, value);
        }

    }

}
