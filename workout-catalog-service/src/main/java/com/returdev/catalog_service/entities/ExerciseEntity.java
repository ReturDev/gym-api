package com.returdev.catalog_service.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
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

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Lob
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
    @JoinColumn(name = "equipment_id")
    private EquipmentEntity equipment;

    @Column(name = "image_url", nullable = false)
    private String exerciseImageUrl;

    @Column(name = "video_url", nullable = false)
    private String exerciseVideoUrl;

    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Column(name = "is_visible", nullable = false)
    private boolean isVisible;

}
