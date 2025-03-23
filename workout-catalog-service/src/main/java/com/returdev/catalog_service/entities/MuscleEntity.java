package com.returdev.catalog_service.entities;

import com.returdev.catalog_service.enums.MuscularGroup;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Represents a muscle entity in the system.
 * This entity is mapped to the 'muscles' table and contains details related to a muscle,
 * including its name and the muscular group it belongs to.
 */
@Entity
@Table(name = "muscles", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "muscularGroup"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MuscleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Size(min = 3, max = 25)
    @NotNull
    @Column(name = "name", unique = true)
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "muscular_group")
    private MuscularGroup muscularGroup;

    @OneToMany(
            mappedBy = "muscle",
            cascade = {CascadeType.REMOVE, CascadeType.PERSIST},
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private List<MuscleInvolvedEntity> muscleInvolvedEntities;

}
