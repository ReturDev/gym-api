package com.returdev.catalog_service.repositories;

import com.returdev.catalog_service.entities.EquipmentEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for managing EquipmentEntity entities.
 * This interface extends JpaRepository to provide CRUD operations and custom queries for EquipmentEntity.
 */
@Repository
public interface EquipmentRepository extends JpaRepository<EquipmentEntity, Long> {

    /**
     * Finds an equipment entity by its name.
     *
     * @param equipmentName the name of the equipment
     * @return an Optional containing the equipment if found, or an empty Optional if not found
     */
    @Query("SELECT e FROM EquipmentEntity e WHERE e.name = :name")
    Optional<EquipmentEntity> findEquipmentByName(@Param("name") String equipmentName);

    /**
     * Checks if an equipment entity with the given name exists.
     *
     * @param name the name of the equipment
     * @return true if an equipment entity with the given name exists, false otherwise
     */
    boolean existsByName(String name);

    /**
     * Updates the name of an equipment entity with the given ID.
     *
     * @param id the ID of the equipment entity to update
     * @param newName the new name to set
     * @return the number of entities updated (should be 1 if the update was successful)
     */
    @Modifying
    @Transactional
    @Query("UPDATE EquipmentEntity e SET e.name = :name WHERE e.id = :id")
    int updateEquipmentName(@Param("id") Long id, @Param("name") String newName);

    /**
     * Updates the image URL of an equipment entity with the given ID.
     *
     * @param id the ID of the equipment entity to update
     * @param imageUrl the new image URL to set
     * @return the number of entities updated (should be 1 if the update was successful)
     */
    @Modifying
    @Transactional
    @Query("UPDATE EquipmentEntity e SET e.imageUrl = :imageUrl WHERE e.id = :id")
    int updateEquipmentImageUrl(@Param("id") Long id, @Param("imageUrl") String imageUrl);

}
