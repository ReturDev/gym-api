package com.returdev.user_service.services.user;

import com.returdev.user_service.enities.UserEntity;
import com.returdev.user_service.repositories.UserRepository;
import com.returdev.utils_library.exceptions.DataBaseOperationException;
import com.returdev.utils_library.exceptions.IncorrectPasswordException;
import com.returdev.utils_library.exceptions.ResourceNotFoundException;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

/**
 * Implementation of the UserService interface for managing user-related operations.
 * This class provides methods for retrieving, updating, and managing user entities.
 */
@RequiredArgsConstructor
@Service
@Validated
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    /**
     * Retrieves a user entity by their email address.
     *
     * @param email the email address of the user
     * @return the user entity associated with the given email
     * @throws ResourceNotFoundException if no user is found with the given email
     */
    @Override
    public UserEntity getUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(ResourceNotFoundException::new);
    }

    /**
     * Retrieves a user entity by their unique ID.
     *
     * @param id the unique identifier of the user
     * @return the user entity associated with the given ID
     * @throws ResourceNotFoundException if no user is found with the given ID
     */
    @Override
    public UserEntity getUserById(UUID id) {
        return userRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    /**
     * Retrieves a paginated list of users by their role name.
     *
     * @param roleName the name of the role
     * @param pageable the pagination information
     * @return a paginated list of user entities with the specified role
     */
    @Override
    public Page<UserEntity> getUsersByRoleName(String roleName, Pageable pageable) {
        return userRepository.findUsersByRoleName(roleName, pageable);
    }

    /**
     * Updates the password of a user identified by their email address.
     *
     * @param email the email address of the user
     * @param oldPassword the current password of the user
     * @param newPassword the new password to set
     * @throws IncorrectPasswordException if the old password is incorrect or the new password matches the current password
     * @throws DataBaseOperationException if the password update operation fails
     */
    @Override
    public void updatePasswordByEmail(String email, String oldPassword, String newPassword) {
        String encodedPassword = getUserByEmail(email).getPassword();

        if (!passwordEncoder.matches(oldPassword, encodedPassword)) {
            throw new IncorrectPasswordException();
        }

        if (passwordEncoder.matches(newPassword, encodedPassword)) {
            throw new IncorrectPasswordException("exception.IncorrectPasswordException.same_current_password.message");
        }

        String newPasswordEncoded = passwordEncoder.encode(newPassword);

        int result = userRepository.updatePasswordByEmail(email, newPasswordEncoded);

        if (result != 1) {
            throwUpdateDataBaseOperationException();
        }
    }

    /**
     * Updates the user information such as username, name, and surnames.
     *
     * @param userId the unique identifier of the user
     * @param username the new username to set
     * @param name the new name to set
     * @param surnames the new surnames to set
     * @throws DataBaseOperationException if any update operation fails
     */
    @Override
    public void updateUserInfo(@NotNull UUID userId, String username, String name, String surnames) {

        existsById(userId);

        if (username != null) {
            updateUsername(userId, username);
        }

        if (name != null) {
            updateName(userId, name);
        }

        if (surnames != null) {
            updateSurnames(userId, surnames);
        }
    }

    /**
     * Changes the role of a user identified by their unique ID.
     *
     * @param userId the unique identifier of the user
     * @param roleName the name of the new role to assign
     */
    @Override
    public void changeRole(UUID userId, String roleName) {

        existsById(userId);

        if (userRepository.changeUserRoleById(userId, roleName) != 1) {
            throwUpdateDataBaseOperationException();
        }
    }

    /**
     * Verifies a user by setting their verification status to true.
     *
     * @param userId the unique identifier of the user
     */
    @Override
    public void verifyUser(UUID userId) {

        existsById(userId);

        if (userRepository.verifyUserById(userId) != 1) {
            throwUpdateDataBaseOperationException();
        }
    }

    /**
     * Disables a user by setting their enabled status to false.
     *
     * @param userId the unique identifier of the user
     */
    @Override
    public void disableUser(UUID userId) {

        existsById(userId);

        if (userRepository.updateEnabledStatusById(userId, false) != 1) {
            throwUpdateDataBaseOperationException();
        }

    }

    /**
     * Enables a user by setting their enabled status to true.
     *
     * @param userId the unique identifier of the user
     */
    @Override
    public void enableUser(UUID userId) {

        existsById(userId);

        if (userRepository.updateEnabledStatusById(userId, true) != 1) {
            throwUpdateDataBaseOperationException();
        }

    }

    /**
     * Saves a new user entity to the database.
     *
     * @param newUser the user entity to save
     * @return the saved user entity
     */
    @Override
    public UserEntity saveUser(UserEntity newUser) {
        return userRepository.save(newUser);
    }

    /**
     * Deletes a user entity identified by their unique ID.
     *
     * @param userId the unique identifier of the user
     */
    @Override
    public void deleteUser(UUID userId) {
        userRepository.deleteById(userId);
    }

    /**
     * Throws a DataBaseOperationException with a predefined message.
     *
     * @throws DataBaseOperationException always thrown by this method
     */
    private void throwUpdateDataBaseOperationException() {
        throw new DataBaseOperationException("exception.DataBaseOperationException.update.message");
    }

    /**
     * Updates the username of a user identified by their ID.
     *
     * @param userId the unique identifier of the user
     * @param newUsername the new username to set
     * @throws DataBaseOperationException if the update operation fails
     */
    private void updateUsername(UUID userId, @Size(min = 3, max = 50) String newUsername) {
        if (userRepository.updateUsernameById(userId, newUsername) != 1) {
            throwUpdateDataBaseOperationException();
        }
    }

    /**
     * Updates the name of a user identified by their ID.
     *
     * @param userId the unique identifier of the user
     * @param newName the new name to set
     * @throws DataBaseOperationException if the update operation fails
     */
    private void updateName(UUID userId, @Size(min = 3, max = 50) String newName) {
        if (userRepository.updateNameById(userId, newName) != 1) {
            throwUpdateDataBaseOperationException();
        }
    }

    /**
     * Updates the surnames of a user identified by their ID.
     *
     * @param userId the unique identifier of the user
     * @param newSurnames the new surnames to set
     * @throws DataBaseOperationException if the update operation fails
     */
    private void updateSurnames(UUID userId, @Size(max = 50) String newSurnames) {
        if (userRepository.updateSurnamesById(userId, newSurnames) != 1) {
            throwUpdateDataBaseOperationException();
        }
    }

    /**
     * Checks if a user exists by their unique ID.
     * If the user does not exist, throws a ResourceNotFoundException.
     *
     * @param userId the unique identifier of the user
     * @throws ResourceNotFoundException if no user is found with the given ID
     */
    private void existsById(UUID userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("exception.ResourceNotFoundException.id.message", userId);
        }
    }

}
