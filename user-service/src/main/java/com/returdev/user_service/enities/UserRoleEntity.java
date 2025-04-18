package com.returdev.user_service.enities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Entity class representing a user role in the system.
 * This entity is mapped to the "user_roles" table in the database.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "user_roles")
public class UserRoleEntity {

    /**
     * The unique identifier for the user role.
     * This value is auto-generated.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the user role.
     */
    @Size(min = 3, max = 25)
    @NotNull
    @Column(name = "role_name",length = 25, updatable = false, unique = true, nullable = false)
    private String roleName;

    /**
     * The list of permissions associated with the user role.
     * This is a many-to-many relationship with the UserPermissionsEntity.
     * The relationship is eagerly fetched and uses a join table named "role_permission".
     */
    @NotEmpty
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "role_permission",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private List<UserPermissionEntity> permissions;

}
