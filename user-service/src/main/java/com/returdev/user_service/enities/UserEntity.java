package com.returdev.user_service.enities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;


/**
 * Entity class representing a user in the system.
 * This entity is mapped to the "users" table in the database.
 */
@Entity
@Table(name = "users")
public class UserEntity {

    /**
     * The unique identifier for the user.
     * This value is auto-generated as a UUID.
     */
    @Id
    @UuidGenerator
    private UUID id;

    /**
     * The username of the user.
     * Must be between 3 and 50 characters and cannot be null.
     */
    @Size(min = 3, max = 50)
    @NotNull
    @Column(name = "username", length = 50, nullable = false)
    private String username;

    /**
     * The first name of the user.
     * Must be between 3 and 50 characters and cannot be null.
     */
    @Size(min = 3, max = 50)
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    /**
     * The surnames of the user.
     * Must not exceed 50 characters and cannot be null.
     */
    @Size(max = 50)
    @Column(name = "surnames", length = 50, nullable = false)
    private String surnames;

    /**
     * The email address of the user.
     * Must be a valid email format, unique, and cannot be null.
     */
    @Email
    @NotNull
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    /**
     * The password of the user.
     * Cannot be blank and must not be null.
     */
    @NotBlank
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * The role of the user.
     * This is a many-to-one relationship with the UserRoleEntity.
     * The role is mandatory and is merged when persisted.
     */
    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "user_role_id", nullable = false)
    private UserRoleEntity userRole;

}
