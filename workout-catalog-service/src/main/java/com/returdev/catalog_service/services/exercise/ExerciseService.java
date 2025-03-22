package com.returdev.catalog_service.services.exercise;


import com.returdev.catalog_service.annotations.validation.ValidId;
import com.returdev.catalog_service.entities.ExerciseEntity;
import com.returdev.catalog_service.entities.MuscleInvolvedEntity;
import com.returdev.catalog_service.enums.MuscleActivationLevel;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Service interface for managing exercise entities.
 * This interface provides methods for CRUD operations and custom queries related to exercises.
 */
@Validated
public interface ExerciseService {

    /**
     * Retrieves an exercise entity by its ID.
     *
     * @param exerciseId the ID of the exercise entity
     * @return the exercise entity with the specified ID
     */
    ExerciseEntity getExerciseEntityById(@ValidId Long exerciseId);

    /**
     * Retrieves all exercise entities, optionally including invisible ones.
     *
     * @param includeInvisible whether to include invisible exercises
     * @param pageable the pagination information
     * @return a page of exercise entities
     */
    Page<ExerciseEntity> getAllExercises(boolean includeInvisible, Pageable pageable);

    /**
     * Retrieves exercise entities whose names contain the specified string.
     *
     * @param name the string to search for in exercise names
     * @param includeInvisibleExercises whether to include invisible exercises
     * @param pageable the pagination information
     * @return a page of exercise entities matching the search criteria
     */
    Page<ExerciseEntity> getExercisesByNameContaining(
            @NotBlank String name,
            boolean includeInvisibleExercises,
            Pageable pageable
    );

    /**
     * Retrieves exercise entities associated with a specific muscle name.
     *
     * @param muscleName the name of the muscle
     * @param includeInvisible whether to include invisible exercises
     * @param pageable the pagination information
     * @return a page of exercise entities associated with the specified muscle name
     */
    Page<ExerciseEntity> getExercisesByMuscleName(
            @NotBlank String muscleName,
            boolean includeInvisible,
            Pageable pageable
    );

    /**
     * Retrieves exercise entities associated with a specific muscle name and activation level.
     *
     * @param muscleName the name of the muscle
     * @param muscleActivationLevel the activation level of the muscle
     * @param includeInvisible whether to include invisible exercises
     * @param pageable the pagination information
     * @return a page of exercise entities matching the specified criteria
     */
    Page<ExerciseEntity> getExercisesByMuscleNameAndActivationLevel(
            @NotBlank String muscleName,
            @NotNull MuscleActivationLevel muscleActivationLevel,
            boolean includeInvisible,
            Pageable pageable
    );

    /**
     * Saves a new exercise entity.
     *
     * @param exercise the exercise entity to save
     * @return the saved exercise entity
     */
    ExerciseEntity saveExercise(@Valid ExerciseEntity exercise);

    /**
     * Updates an existing exercise entity.
     *
     * @param exerciseId the ID of the exercise entity to update
     * @param newDescription the new description to set
     * @param isBenchRequired the new bench requirement to set
     * @param musclesInvolved the new muscles involved to set
     * @param newImageUrl the new image URL to set
     * @param newVideoUrl the new video URL to set
     * @param isVisible the new visibility status to set
     * @return true if the update was successful, false otherwise
     */
    @Transactional
    boolean updateExercise(
            @ValidId Long exerciseId,
            String newDescription,
            Boolean isBenchRequired,
            List<MuscleInvolvedEntity> musclesInvolved,
            String newImageUrl,
            String newVideoUrl,
            Boolean isVisible
    );

    /**
     * Deletes an exercise entity by its ID.
     *
     * @param id the ID of the exercise entity to delete
     */
    void deleteExerciseById(Long id);

}
