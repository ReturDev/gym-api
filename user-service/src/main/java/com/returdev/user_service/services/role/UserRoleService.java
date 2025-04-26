package com.returdev.user_service.services.role;

import com.returdev.user_service.entities.UserPermissionEntity;
import com.returdev.user_service.entities.UserRoleEntity;
import com.returdev.utils_library.annotations.ValidId;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

/**
 * Service interface for managing user roles.
 * Provides methods for retrieving, modifying, and saving user roles and their permissions.
 */
public interface UserRoleService {

    /**
     * Retrieves a user role by its name.
     *
     * @param name the name of the role
     * @return the user role entity associated with the given name
     */
    UserRoleEntity getRoleByName(@NotBlank String name);

    /**
     * Retrieves a user role by its unique ID.
     *
     * @param id the unique identifier of the role
     * @return the user role entity associated with the given ID
     */
    UserRoleEntity getRoleById(@ValidId Long id);

    /**
     * Retrieves all user roles.
     *
     * @return a list of all user role entities
     */
    List<UserRoleEntity> getAllRoles();

    /**
     * Modifies the permissions of a user role.
     *
     * @param roleId the unique identifier of the role
     * @param permissions the list of permissions to assign to the role
     */
    @Transactional
    void modifyRolePermissions(@ValidId Long roleId, @NotEmpty List<UserPermissionEntity> permissions);

    /**
     * Saves a new user role entity to the database.
     *
     * @param userRole the user role entity to save
     * @return the saved user role entity
     */
    UserRoleEntity saveRole(@Valid UserRoleEntity userRole);

}
