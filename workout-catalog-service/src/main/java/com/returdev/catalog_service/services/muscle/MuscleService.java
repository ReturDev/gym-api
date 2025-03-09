package com.returdev.catalog_service.services.muscle;

import com.returdev.catalog_service.entities.MuscleEntity;
import com.returdev.catalog_service.enums.MuscularGroup;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Service interface for managing muscle entities.
 */
@Validated
public interface MuscleService {

    /**
     * Saves a muscle entity.
     *
     * @param muscleEntity the muscle entity to save
     * @return the saved muscle entity
     */
    MuscleEntity saveMuscle(@Valid MuscleEntity muscleEntity);

    /**
     * Retrieves a muscle entity by its ID.
     *
     * @param id the ID of the muscle entity
     * @return the muscle entity with the specified ID
     */
    MuscleEntity getMuscleById(@NotNull(message = "{validation.id.not_null_required.message}") Long id);

    /**
     * Retrieves a muscle entity by its name.
     *
     * @param name the name of the muscle entity
     * @return the muscle entity with the specified name
     */
    MuscleEntity getMuscleByName(@Size(min = 3, max = 25, message = "{validation.size.message}")String name);

    /**
     * Retrieves all muscle entities.
     *
     * @return a list of all muscle entities
     */
    List<MuscleEntity> getAllMuscles();

    /**
     * Retrieves muscle entities of a specific muscular group.
     *
     * @param muscularGroup the muscular group of the muscle entities
     * @return a list of muscle entities belonging to the specified muscular group
     */
    List<MuscleEntity> getMusclesOfMuscleGroup(
            @NotNull(message = "{validation.id.not_null_required.message}") MuscularGroup muscularGroup
    );


    /**
     * Deletes a muscle entity by its ID.
     *
     * @param id the ID of the muscle entity to delete
     */
    void deleteMuscleById(@NotNull(message = "{validation.id.not_null_required.message}") Long id);
}
