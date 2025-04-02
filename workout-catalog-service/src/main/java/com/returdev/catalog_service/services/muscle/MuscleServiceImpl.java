package com.returdev.catalog_service.services.muscle;

import com.returdev.catalog_service.entities.MuscleEntity;
import com.returdev.catalog_service.entities.MuscleInvolvedEntity;
import com.returdev.catalog_service.enums.MuscleActivationLevel;
import com.returdev.catalog_service.enums.MuscularGroup;
import com.returdev.catalog_service.repositories.MuscleRepository;
import com.returdev.utils_library.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementation for managing muscle entities.
 * This class provides methods for CRUD operations and custom queries related to muscle entities.
 */
@Service
@RequiredArgsConstructor
public class MuscleServiceImpl implements MuscleService {

    private final MuscleRepository muscleRepository;

    /**
     * Saves a new muscle entity.
     *
     * @param muscleEntity the muscle entity to save
     * @return the saved muscle entity
     * @throws IllegalArgumentException if the muscle entity has an ID (indicating it already exists)
     */
    @Override
    public MuscleEntity saveMuscle(MuscleEntity muscleEntity) {
        muscleEntity.setId(null);
        muscleEntity.setMuscleInvolvedEntities(
                List.of(
                        new MuscleInvolvedEntity(MuscleActivationLevel.LOW),
                        new MuscleInvolvedEntity(MuscleActivationLevel.MEDIUM),
                        new MuscleInvolvedEntity(MuscleActivationLevel.HIGH)
                )
        );
        return muscleRepository.save(muscleEntity);
    }

    /**
     * Retrieves all muscle entities.
     *
     * @return a list of all muscle entities
     */
    @Override
    public List<MuscleEntity> getAllMuscles() {
        return muscleRepository.findAll();
    }

    /**
     * Retrieves muscle entities of a specific muscular group.
     *
     * @param muscularGroup the muscular group of the muscle entities
     * @return a list of muscle entities belonging to the specified muscular group
     */
    @Override
    public List<MuscleEntity> getMusclesOfMuscleGroup(MuscularGroup muscularGroup) {
        return muscleRepository.findMusclesOfMuscularGroup(muscularGroup);
    }

    /**
     * Retrieves a muscle entity by its ID.
     *
     * @param id the ID of the muscle entity
     * @return the muscle entity with the specified ID
     * @throws EntityNotFoundException if no muscle entity with the specified ID is found
     */
    @Override
    public MuscleEntity getMuscleById(Long id) {
        return muscleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    /**
     * Retrieves a muscle entity by its name.
     *
     * @param name the name of the muscle entity
     * @return the muscle entity with the specified name
     * @throws EntityNotFoundException if no muscle entity with the specified name is found
     */
    @Override
    public MuscleEntity getMuscleByName(String name) {
        return muscleRepository.findMuscleByName(name).orElseThrow(() -> new ResourceNotFoundException(name));
    }

    /**
     * Deletes a muscle entity by its ID.
     *
     * @param id the ID of the muscle entity to delete
     */
    @Override
    public void deleteMuscleById(Long id) {
        muscleRepository.deleteById(id);
    }
}
