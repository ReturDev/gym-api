package com.returdev.user_service.services.role;

import com.returdev.user_service.entities.UserPermissionEntity;
import com.returdev.user_service.entities.UserRoleEntity;
import com.returdev.user_service.repositories.UserRoleRepository;
import com.returdev.utils_library.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Service implementation for managing user roles.
 * This class provides methods to handle CRUD operations and permission modifications for user roles.
 */
@Service
@RequiredArgsConstructor
@Validated
public class UserRoleServiceImpl implements UserRoleService {

    private UserRoleRepository userRoleRepository;

    /**
     * Retrieves a user role by its name.
     *
     * @param name the name of the role
     * @return the user role entity associated with the given name
     */
    @Override
    public UserRoleEntity getRoleByName(String name) {
        return userRoleRepository.findByRoleName(name);
    }

    /**
     * Retrieves a user role by its unique ID.
     *
     * @param id the unique identifier of the role
     * @return the user role entity associated with the given ID
     * @throws ResourceNotFoundException if no role is found with the given ID
     */
    @Override
    public UserRoleEntity getRoleById(Long id) {
        return userRoleRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("exception.ResourceNotFoundException.id.message", id)
        );
    }

    /**
     * Retrieves all user roles.
     *
     * @return a list of all user role entities
     */
    @Override
    public List<UserRoleEntity> getAllRoles() {
        return userRoleRepository.findAll();
    }

    /**
     * Modifies the permissions of a user role.
     *
     * @param roleId the unique identifier of the role
     * @param permissions the list of permissions to assign to the role
     */
    @Override
    public void modifyRolePermissions(Long roleId, List<UserPermissionEntity> permissions) {
        UserRoleEntity role = getRoleById(roleId);
        role.setPermissions(permissions);
        userRoleRepository.save(role);
    }

    /**
     * Saves a new user role entity to the database.
     *
     * @param userRole the user role entity to save
     * @return the saved user role entity
     */
    @Override
    public UserRoleEntity saveRole(UserRoleEntity userRole) {
        return userRoleRepository.save(userRole);
    }
}
