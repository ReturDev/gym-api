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
 * {@inheritDoc}
 */
@Service
@RequiredArgsConstructor
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public ExerciseEntity getExerciseEntityById(Long exerciseId) {
        return exerciseRepository.findById(exerciseId).orElseThrow(() -> new EntityNotFoundException("")); //TODO Add message
    }

    /**
     * {@inheritDoc}
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
     * {@inheritDoc}
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
     * {@inheritDoc}
     */
    @Override
    public Page<ExerciseEntity> getExercisesByMuscleName(String muscleName, boolean includeInvisible, Pageable pageable) {
        return exerciseRepository.findExercisesByMuscleName(muscleName, includeInvisible, pageable);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<ExerciseEntity> getExercisesByMuscleNameAndActivationLevel(String muscleName, MuscleActivationLevel muscleActivationLevel, boolean includeInvisible, Pageable pageable) {
        return exerciseRepository.findExercisesByMuscleNameAndActivationLevel(muscleName, muscleActivationLevel, includeInvisible, pageable);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ExerciseEntity saveExercise(ExerciseEntity exercise) {
        return exerciseRepository.save(exercise);
    }

    /**
     * {@inheritDoc}
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

        existsById(exerciseId);

        boolean updated = false;

        if (newDescription != null) {
            updated |= exerciseRepository.updateExerciseDescription(exerciseId, newDescription) == 1;
        }

        if (isBenchRequired != null) {
            updated |= exerciseRepository.updateIsBenchRequired(exerciseId, isBenchRequired) == 1;
        }

        if (musclesInvolved != null) {
            updated |= exerciseRepository.updateMusclesInvolved(exerciseId, musclesInvolved) == 1;
        }

        if (newImageUrl != null) {
            updated |= exerciseRepository.updateExerciseImageUrl(exerciseId, newImageUrl) == 1;
        }

        if (newVideoUrl != null) {
            updated |= exerciseRepository.updateExerciseVideoUrl(exerciseId, newVideoUrl) == 1;
        }

        if (isVisible != null) {
            updated |= exerciseRepository.updateExerciseVisibility(exerciseId, isVisible) == 1;
        }

        return updated;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteExerciseById(Long id) {
        exerciseRepository.deleteById(id);
    }

    /**
     * Checks if an exercise entity with the given ID exists.
     *
     * @param id the ID of the exercise entity
     * @throws EntityNotFoundException if the exercise entity is not found
     */
    private void existsById(Long id) {
        if (!exerciseRepository.existsById(id)) {
            throw new EntityNotFoundException(""); //TODO Add message
        }
    }

}
