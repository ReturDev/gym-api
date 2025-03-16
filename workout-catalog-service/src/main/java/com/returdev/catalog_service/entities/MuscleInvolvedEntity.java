package com.returdev.catalog_service.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.returdev.catalog_service.enums.MuscleActivationLevel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents an entity for muscles involved in exercises.
 * This entity is mapped to the 'muscles_involved' table and contains details about the muscle and its activation level.
 * The entity enforces a unique constraint on the combination of 'muscle_id' and 'activation_level'.
 */
@Entity
@Table(
        name = "muscles_involved",
        uniqueConstraints = @UniqueConstraint(columnNames = {"muscle_id", "activation_level"})
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MuscleInvolvedEntity {
    
    public MuscleInvolvedEntity(MuscleActivationLevel activationLevel) {
        this.activationLevel = activationLevel;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "muscle_id")
    private MuscleEntity muscle;

    @Enumerated(EnumType.STRING)
    @Column(name = "activation_level", nullable = false)
    private MuscleActivationLevel activationLevel;

}