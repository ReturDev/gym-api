package com.returdev.catalog_service.repositories;

import com.returdev.catalog_service.entities.MuscleEntity;
import com.returdev.catalog_service.enums.MuscularGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing MuscleEntity entities.
 * This interface extends JpaRepository to provide CRUD operations and custom queries for MuscleEntity.
 */
@Repository
public interface MuscleRepository extends JpaRepository<MuscleEntity, Long> {

    /**
     * Finds muscle entities by their muscular group.
     *
     * @param muscularGroup the muscular group of the muscle entities
     * @return a list of muscle entities belonging to the specified muscular group
     */
    @Query("SELECT m FROM MuscleEntity m WHERE m.muscularGroup = :muscularGroup")
    List<MuscleEntity> findMusclesOfMuscularGroup(@Param("muscularGroup") MuscularGroup muscularGroup);

    /**
     * Finds a muscle entity by its name.
     *
     * @param name the name of the muscle entity
     * @return an Optional containing the muscle entity if found, or an empty Optional if not found
     */
    @Query("SELECT m FROM MuscleEntity m WHERE m.name = :name")
    Optional<MuscleEntity> findMuscleByName(@Param("name") String muscleName);

}
