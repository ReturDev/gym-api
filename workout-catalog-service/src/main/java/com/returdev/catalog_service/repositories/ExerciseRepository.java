package com.returdev.catalog_service.repositories;

import com.returdev.catalog_service.entities.ExerciseEntity;
import com.returdev.catalog_service.entities.MuscleInvolvedEntity;
import com.returdev.catalog_service.enums.MuscleActivationLevel;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing ExerciseEntity entities.
 * This interface provides methods for CRUD operations and custom queries related to exercises.
 */
@Repository
public interface ExerciseRepository extends JpaRepository<ExerciseEntity, Long> {

    /**
     * Retrieves all visible exercise entities.
     *
     * @param pageable the pagination information
     * @return a page of visible exercise entities
     */
    @Query("SELECT e FROM ExerciseEntity e WHERE e.isVisible = true")
    Page<ExerciseEntity> findVisibleExercises(Pageable pageable);

    /**
     * Retrieves exercise entities whose names contain the specified string.
     *
     * @param nameContaining the string to search for in exercise names
     * @param includeInvisible whether to include invisible exercises
     * @param pageable the pagination information
     * @return a page of exercise entities matching the search criteria
     */
    @Query(
            "SELECT e " +
            "FROM ExerciseEntity e " +
            "WHERE (:includeInvisible = true OR e.isVisible = true) AND e.name LIKE CONCAT('%', :name, '%')"
    )
    Page<ExerciseEntity> findExerciseByNameContaining(
            @Param("name") String nameContaining,
            @Param("includeInvisible") boolean includeInvisible,
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
    @Query(
            "SELECT e " +
            "FROM ExerciseEntity e " +
            "JOIN e.musclesInvolved mi JOIN mi.muscle mu " +
            "WHERE (:includeInvisible = true OR e.isVisible = true) AND mu.name = :muscleName"
    )
    Page<ExerciseEntity> findExercisesByMuscleName(
            @Param("muscleName") String muscleName,
            @Param("includeInvisible") boolean includeInvisible,
            Pageable pageable
    );

    /**
     * Retrieves exercise entities associated with a specific muscle name and activation level.
     *
     * @param muscleName the name of the muscle
     * @param activationLevel the activation level of the muscle
     * @param includeInvisible whether to include invisible exercises
     * @param pageable the pagination information
     * @return a page of exercise entities matching the specified criteria
     */
    @Query(
            "SELECT e " +
            "FROM ExerciseEntity e " +
            "JOIN e.musclesInvolved mi " +
            "JOIN mi.muscle mu " +
            "WHERE (:includeInvisible = true OR e.isVisible = true) " +
                    "AND mu.name = :muscleName AND mi.activationLevel = :activationLevel"
    )
    Page<ExerciseEntity> findExercisesByMuscleNameAndActivationLevel(
            @Param("muscleName") String muscleName,
            @Param("activationLevel") MuscleActivationLevel activationLevel,
            @Param("includeInvisible") boolean includeInvisible,
            Pageable pageable
    );

    /**
     * Updates the description of an exercise entity.
     *
     * @param exerciseId the ID of the exercise entity to update
     * @param exerciseDescription the new description to set
     * @return the number of entities updated
     */
    @Modifying
    @Transactional
    @Query("UPDATE ExerciseEntity e SET e.description = :description WHERE e.id = :exerciseId")
    int updateExerciseDescription(@Param("exerciseId") Long exerciseId, @Param("description") String exerciseDescription);

    /**
     * Updates the bench requirement of an exercise entity.
     *
     * @param exerciseId the ID of the exercise entity to update
     * @param isBenchRequired the new bench requirement to set
     * @return the number of entities updated
     */
    @Modifying
    @Transactional
    @Query("UPDATE ExerciseEntity e SET e.benchRequired =:benchRequired WHERE e.id = :exerciseId")
    int updateIsBenchRequired(@Param("exerciseId") Long exerciseId, @Param("benchRequired") boolean isBenchRequired);

    /**
     * Updates the muscles involved in an exercise entity.
     *
     * @param exerciseId the ID of the exercise entity to update
     * @param musclesInvolved the new muscles involved to set
     * @return the number of entities updated
     */
    @Modifying
    @Transactional
    @Query("UPDATE ExerciseEntity e SET e.musclesInvolved = :musclesInvolved WHERE e.id = :exerciseId")
    int updateMusclesInvolved(@Param("exerciseId") Long exerciseId, @Param("musclesInvolved") List<MuscleInvolvedEntity> musclesInvolved);

    /**
     * Updates the image URL of an exercise entity.
     *
     * @param exerciseId the ID of the exercise entity to update
     * @param imageUrl the new image URL to set
     * @return the number of entities updated
     */
    @Modifying
    @Transactional
    @Query("UPDATE ExerciseEntity e SET e.exerciseImageUrl = :imageUrl WHERE e.id = :exerciseId")
    int updateExerciseImageUrl(@Param("exerciseId") Long exerciseId, @Param("imageUrl") String imageUrl);

    /**
     * Updates the video URL of an exercise entity.
     *
     * @param exerciseId the ID of the exercise entity to update
     * @param videoUrl the new video URL to set
     * @return the number of entities updated
     */
    @Modifying
    @Transactional
    @Query("UPDATE ExerciseEntity e SET e.exerciseVideoUrl = :videoUrl WHERE e.id = :exerciseId")
    int updateExerciseVideoUrl(@Param("exerciseId") Long exerciseId, @Param("videoUrl") String videoUrl);

    /**
     * Updates the visibility of an exercise entity.
     *
     * @param exerciseId the ID of the exercise entity to update
     * @param isVisible the new visibility status to set
     * @return the number of entities updated
     */
    @Modifying
    @Transactional
    @Query("UPDATE ExerciseEntity e SET e.isVisible = :isVisible WHERE e.id = :exerciseId")
    int updateExerciseVisibility(@Param("exerciseId") Long exerciseId,@Param("isVisible") boolean isVisible);

}
