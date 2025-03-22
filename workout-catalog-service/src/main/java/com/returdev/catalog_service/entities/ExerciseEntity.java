package com.returdev.catalog_service.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


/**
 * Represents an exercise template entity in the system.
 * This entity is mapped to the 'exercise_templates' table and contains various details related to an exercise,
 * including its name, description, required equipment, muscles involved, image, video URL, visibility, and creation date.
 * The entity enforces a unique constraint on the combination of 'name', 'equipment_id', and 'is_bench_required'.
 */
@Entity
@Table(
        name = "exercise_templates",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"name", "equipment_id", "is_bench_required"}
        )
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3,max = 50)
    @NotNull
    @Column(name = "name", nullable = false, length = 50, updatable = false)
    private String name;

    @Lob
    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "is_bench_required", nullable = false)
    private boolean benchRequired;

    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.MERGE
    )
    @JoinTable(
            name = "exercises_muscles_involved",
            joinColumns = @JoinColumn(name = "exercise_id"),
            inverseJoinColumns = @JoinColumn(name = "muscle_involved_id")
    )
    private List<MuscleInvolvedEntity> musclesInvolved;

    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "equipment_id", updatable = false)
    private EquipmentEntity equipment;

    @NotNull
    @Column(name = "image_url", nullable = false)
    private String exerciseImageUrl;

    @NotNull
    @Column(name = "video_url", nullable = false)
    private String exerciseVideoUrl;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "is_visible", nullable = false)
    private boolean isVisible;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

}
