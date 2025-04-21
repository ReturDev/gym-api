package com.returdev.user_service.repositories;

import com.returdev.user_service.enities.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing `UserRoleEntity` objects in the database.
 * Extends the `JpaRepository` to provide CRUD operations and custom queries.
 */
@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

    /**
     * Finds a user role by its name.
     *
     * @param name the name of the role
     * @return the `UserRoleEntity` matching the given name
     */
    UserRoleEntity findByRoleName(String name);

}
