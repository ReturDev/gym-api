package com.returdev.user_service.entities;

import com.returdev.user_service.enums.UserPermission;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class representing a user permission in the system.
 * This entity is mapped to the "user_permissions" table in the database.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "user_permissions")
public class UserPermissionEntity {

    /**
     * The unique identifier for the user permission.
     * This value is auto-generated.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The specific permission associated with this entity.
     * This field is not nullable, cannot be updated, and must be unique.
     */
    @NotNull
    @Column(name = "permission", updatable = false, nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private UserPermission permission;

}
