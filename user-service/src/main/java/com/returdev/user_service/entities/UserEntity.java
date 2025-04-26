package com.returdev.user_service.entities;

import com.returdev.user_service.annotations.validations.ValidPassword;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;


/**
 * Entity class representing a user in the system.
 * This entity is mapped to the "users" table in the database.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
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
    @NotBlank
    @Column(name = "username", length = 50, nullable = false)
    private String username;

    /**
     * The first name of the user.
     * Must be between 3 and 50 characters and cannot be null.
     */
    @Size(min = 3, max = 50)
    @NotBlank
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    /**
     * The surnames of the user.
     * Must not exceed 50 characters and cannot be null.
     */
    @Size(max = 50)
    @NotNull
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
    @ValidPassword
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

    /**
     * Indicates whether the user's email is verified.
     */
    private boolean isVerified;

    /**
     * Indicates whether the user is enabled.
     * Defaults to true.
     */
    private boolean isEnabled = true;

    /**
     * Constructs a new UserEntity with the specified parameters.
     *
     * @param id the unique identifier of the user
     * @param username the username of the user
     * @param name the first name of the user
     * @param surnames the surnames of the user
     * @param email the email address of the user
     * @param password the password of the user
     * @param userRole the role of the user
     */
    public UserEntity(UUID id, String username, String name, String surnames, String email, String password, UserRoleEntity userRole) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.surnames = surnames;
        this.email = email;
        this.password = password;
        this.userRole = userRole;
    }

    public UserEntity(UUID id, String username, String name, String surnames, String email, UserRoleEntity userRole, boolean isVerified, boolean isEnabled) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.surnames = surnames;
        this.email = email;
        this.userRole = userRole;
        this.isVerified = isVerified;
        this.isEnabled = isEnabled;
    }
}