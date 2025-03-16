package com.returdev.catalog_service.services.exercise;

import com.returdev.catalog_service.entities.ExerciseEntity;
import com.returdev.catalog_service.entities.MuscleInvolvedEntity;
import com.returdev.catalog_service.enums.MuscleActivationLevel;
import com.returdev.catalog_service.repositories.ExerciseRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementation for managing exercise entities.
 * This class provides methods for CRUD operations and custom queries related to exercises.
 */
@Service
@RequiredArgsConstructor
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;

    /**
     * Retrieves an exercise entity by its ID.
     *
     * @param exerciseId the ID of the exercise entity
     * @return the exercise entity with the specified ID
     * @throws EntityNotFoundException if the exercise entity is not found
     */
    @Override
    public ExerciseEntity getExerciseEntityById(Long exerciseId) {
        return exerciseRepository.findById(exerciseId).orElseThrow(() -> new EntityNotFoundException("")); //TODO Add message
    }

    /**
     * Retrieves all exercise entities, optionally including invisible ones.
     *
     * @param includeInvisible whether to include invisible exercises
     * @param pageable the pagination information
     * @return a page of exercise entities
     */
    @Override
    public Page<ExerciseEntity> getAllExercises(boolean includeInvisible, Pageable pageable) {
        if (includeInvisible) {
            return exerciseRepository.findAll(pageable);
        } else {
            return exerciseRepository.findVisibleExercises(pageable);
        }
    }

    /**
     * Retrieves exercise entities whose names contain the specified string.
     *
     * @param name the string to search for in exercise names
     * @param includeInvisibleExercises whether to include invisible exercises
     * @param pageable the pagination information
     * @return a page of exercise entities matching the search criteria
     */
    @Override
    public Page<ExerciseEntity> getExercisesByNameContaining(
            String name,
            boolean includeInvisibleExercises,
            Pageable pageable
    ) {
        return exerciseRepository.findExerciseByNameContaining(name, includeInvisibleExercises, pageable);
    }

    /**
     * Retrieves exercise entities associated with a specific muscle name.
     *
     * @param muscleName the name of the muscle
     * @param includeInvisible whether to include invisible exercises
     * @param pageable the pagination information
     * @return a page of exercise entities associated with the specified muscle name
     */
    @Override
    public Page<ExerciseEntity> getExercisesByMuscleName(String muscleName, boolean includeInvisible, Pageable pageable) {
        return exerciseRepository.findExercisesByMuscleName(muscleName, includeInvisible, pageable);
    }

    /**
     * Retrieves exercise entities associated with a specific muscle name and activation level.
     *
     * @param muscleName the name of the muscle
     * @param muscleActivationLevel the activation level of the muscle
     * @param includeInvisible whether to include invisible exercises
     * @param pageable the pagination information
     * @return a page of exercise entities matching the specified criteria
     */
    @Override
    public Page<ExerciseEntity> getExercisesByMuscleNameAndActivationLevel(String muscleName, MuscleActivationLevel muscleActivationLevel, boolean includeInvisible, Pageable pageable) {
        return exerciseRepository.findExercisesByMuscleNameAndActivationLevel(muscleName, muscleActivationLevel, includeInvisible, pageable);
    }

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
    @Override
    public boolean updateExercise(
            Long exerciseId,
            String newDescription,
            Boolean isBenchRequired,
            List<MuscleInvolvedEntity> musclesInvolved,
            String newImageUrl,
            String newVideoUrl,
            Boolean isVisible
    ) {

        boolean updated = false;

        if (newDescription != null) {
            updated = exerciseRepository.updateExerciseDescription(exerciseId, newDescription) == 1;
        }

        if (isBenchRequired != null) {
            updated = exerciseRepository.updateIsBenchRequired(exerciseId, isBenchRequired) == 1;
        }

        if (musclesInvolved != null) {
            updated = exerciseRepository.updateMusclesInvolved(exerciseId, musclesInvolved) == 1;
        }

        if (newImageUrl != null) {
            updated = exerciseRepository.updateExerciseImageUrl(exerciseId, newImageUrl) == 1;
        }

        if (newVideoUrl != null) {
            updated = exerciseRepository.updateExerciseVideoUrl(exerciseId, newImageUrl) == 1;
        }

        if (isVisible != null) {
            updated = exerciseRepository.updateExerciseVisibility(exerciseId, isVisible) == 1;
        }

        return updated;
    }
}
