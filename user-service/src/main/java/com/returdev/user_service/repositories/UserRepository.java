package com.returdev.user_service.repositories;

import com.returdev.user_service.entities.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for managing `UserEntity` objects in the database.
 * Extends the `JpaRepository` to provide CRUD operations and custom queries.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    /**
     * Finds a user by their email address.
     *
     * @param email the email address of the user
     * @return an `Optional` containing the `UserEntity` if found, or empty if not
     */
    @Query("SELECT e FROM UserEntity e WHERE e.email = :email")
    Optional<UserEntity> findUserByEmail(@Param("email") String email);

    /**
     * Finds users by their role name with pagination.
     *
     * @param roleName the name of the role
     * @param pageable the pagination information
     * @return a `Page` of `UserEntity` objects matching the role name
     */
    @Query("""
        SELECT new com.returdev.user_service.entities.UserEntity(
            e.id, e.username, e.name, e.surnames,
            e.email, e.userRole, e.isVerified, e.isEnabled
        )
        FROM UserEntity e
        JOIN e.userRole ur
        WHERE ur.roleName = :roleName
    """)
    Page<UserEntity> findUsersByRoleName(@Param("roleName") String roleName, Pageable pageable);

    /**
     * Updates the password of a user identified by their email address.
     *
     * @param email the email address of the user
     * @param newPassword the new password to set
     * @return the number of rows affected
     */
    @Modifying
    @Transactional
    @Query("UPDATE UserEntity e SET e.password = :newPassword WHERE e.email = :email")
    int updatePasswordByEmail(@Param("email") String email, @Param("newPassword") String newPassword);

    /**
     * Updates the username of a user identified by their ID.
     *
     * @param id the ID of the user
     * @param newUsername the new username to set
     * @return the number of rows affected
     */
    @Modifying
    @Transactional
    @Query("UPDATE UserEntity e SET e.username = :newUsername WHERE e.id = :id")
    int updateUsernameById(@Param("id") UUID id, @Param("newUsername") String newUsername);

    /**
     * Updates the name of a user identified by their ID.
     *
     * @param id the ID of the user
     * @param newName the new name to set
     * @return the number of rows affected
     */
    @Modifying
    @Transactional
    @Query("UPDATE UserEntity e SET e.name = :newName WHERE e.id = :id")
    int updateNameById(@Param("id") UUID id, @Param("newName") String newName);

    /**
     * Updates the surnames of a user identified by their ID.
     *
     * @param id the ID of the user
     * @param newSurnames the new surnames to set
     * @return the number of rows affected
     */
    @Modifying
    @Transactional
    @Query("UPDATE UserEntity e SET e.surnames = :newSurnames WHERE e.id = :id")
    int updateSurnamesById(@Param("id") UUID id, @Param("newSurnames") String newSurnames);

    /**
     * Changes the role of a user identified by their ID.
     *
     * @param id the ID of the user
     * @param newRoleName the name of the new role to assign
     * @return the number of rows affected
     */
    @Modifying
    @Transactional
    @Query("""
            UPDATE UserEntity e
            SET e.userRole = (SELECT ur FROM UserRoleEntity ur WHERE ur.roleName = :newRoleName)
            WHERE e.id = :id
            """)
    int changeUserRoleById(@Param("id") UUID id, @Param("newRoleName") String newRoleName);

    /**
     * Verifies a user by setting their `isVerified` field to true.
     *
     * @param id the ID of the user
     * @return the number of rows affected
     */
    @Modifying
    @Transactional
    @Query("UPDATE UserEntity e SET e.isVerified = true WHERE e.id = :id")
    int verifyUserById(@Param("id") UUID id);

    /**
     * Updates the enabled status of a user identified by their ID.
     *
     * @param id the ID of the user
     * @param isEnabled the new enabled status to set
     * @return the number of rows affected
     */
    @Modifying
    @Transactional
    @Query("UPDATE UserEntity e SET e.isEnabled = :isEnabled WHERE e.id = :id")
    int updateEnabledStatusById(@Param("id") UUID id, boolean isEnabled);

    /**
     * Checks if a user with the specified email address exists in the database.
     *
     * @param email the email address to check
     * @return true if a user with the given email exists, false otherwise
     */
    boolean existsByEmail(String email);

}
