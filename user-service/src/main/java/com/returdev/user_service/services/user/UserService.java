package com.returdev.user_service.services.user;

import com.returdev.user_service.annotations.validations.ValidPassword;
import com.returdev.user_service.entities.UserEntity;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

/**
 * Service interface for managing user-related operations.
 * This interface defines methods for retrieving, updating, and managing user entities.
 */
public interface UserService {


    /**
         * Retrieves a user entity by their email address.
         * If the `includePassword` flag is false, the user's password is excluded from the returned entity.
         *
         * @param email the email address of the user
         * @param includePassword a flag indicating whether to include the user's password in the response
         * @return the user entity associated with the given email address
         */
        UserEntity getUserByEmail(@Email String email, boolean includePassword);

    /**
     * Retrieves a user entity by their unique ID.
     *
     * @param id the unique identifier of the user
     * @return the user entity associated with the given ID
     */
    UserEntity getUserById(@NotNull UUID id);

    /**
     * Retrieves a paginated list of users by their role name.
     *
     * @param roleName the name of the role
     * @param pageable the pagination information
     * @return a paginated list of user entities with the specified role
     */
    Page<UserEntity> getUsersByRoleName(@NotBlank String roleName, Pageable pageable);

    /**
     * Updates the password of a user identified by their email address.
     *
     * @param email the email address of the user
     * @param oldPassword the current password of the user
     * @param newPassword the new password to set
     */
    @Transactional
    void updatePasswordByEmail(@Email String email, @NotBlank String oldPassword, @ValidPassword String newPassword);

    /**
     * Updates the user information such as username, name, and surnames.
     *
     * @param userId the unique identifier of the user
     * @param username the new username to set
     * @param name the new name to set
     * @param surnames the new surnames to set
     */
    @Transactional
    void updateUserInfo(@NotNull UUID userId, String username, String name, String surnames);

    /**
     * Changes the role of a user identified by their unique ID.
     *
     * @param userId the unique identifier of the user
     * @param roleName the name of the new role to assign
     */
    @Transactional
    void changeRole(@NotNull UUID userId, @NotBlank String roleName);

    /**
     * Verifies a user by setting their verification status to true.
     *
     * @param userId the unique identifier of the user
     */
    @Transactional
    void verifyUser(@NotNull UUID userId);

    /**
     * Disables a user by setting their enabled status to false.
     *
     * @param userId the unique identifier of the user
     */
    @Transactional
    void disableUser(@NotNull UUID userId);

    /**
     * Enables a user by setting their enabled status to true.
     *
     * @param userId the unique identifier of the user
     */
    @Transactional
    void enableUser(@NotNull UUID userId);

    /**
     * Saves a new user entity to the database.
     *
     * @param newUser the user entity to save
     * @return the saved user entity
     */
    UserEntity saveUser(@Valid UserEntity newUser);

    /**
     * Deletes a user entity identified by their unique ID.
     *
     * @param userId the unique identifier of the user
     */
    void deleteUser(@NotNull UUID userId);

}
