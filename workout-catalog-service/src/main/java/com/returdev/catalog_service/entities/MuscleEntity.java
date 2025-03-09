package com.returdev.catalog_service.entities;

import com.returdev.catalog_service.enums.MuscularGroup;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a muscle entity in the system.
 * This entity is mapped to the 'muscles' table and contains details related to a muscle,
 * including its name and the muscular group it belongs to.
 */
@Entity
@Table(name = "muscles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MuscleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Size(min = 3, max = 25, message = "{validation.size.message}")
    @NotNull(message = "{validation.not_null_required.message}")
    @Column(name = "name", unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "muscular_group")
    @NotNull(message = "{validation.not_null_required.message}")
    private MuscularGroup muscularGroup;

}
