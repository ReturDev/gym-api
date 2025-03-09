package com.returdev.catalog_service.services.equipment;

import com.returdev.catalog_service.entities.EquipmentEntity;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Service interface for managing equipment entities.
 * This interface provides methods for CRUD operations and custom queries related to equipment.
 */
@Validated
public interface EquipmentService {

    /**
     * Saves a new equipment entity.
     *
     * @param equipment the equipment entity to save
     * @return the saved equipment entity
     * @throws IllegalArgumentException if the equipment entity is invalid
     */
    EquipmentEntity saveEquipment(EquipmentEntity equipment) throws IllegalArgumentException;

    /**
     * Retrieves all equipment entities.
     *
     * @return a list of all equipment entities
     */
    List<EquipmentEntity> getAllEquipments();

    /**
     * Retrieves an equipment entity by its ID.
     *
     * @param id the ID of the equipment entity
     * @return the equipment entity with the specified ID
     */
    EquipmentEntity getEquipmentById(
            @NotNull(message = "{validation.id.not_null_required.message}") Long id
    );

    /**
     * Retrieves an equipment entity by its name.
     *
     * @param name the name of the equipment entity
     * @return the equipment entity with the specified name
     */
    EquipmentEntity getEquipmentByName(
            @NotBlank(message = "{validation.not_blank_message}") String name
    );

    /**
     * Checks if an equipment entity with the given name exists.
     *
     * @param name the name of the equipment entity
     * @return true if an equipment entity with the given name exists, false otherwise
     */
    boolean existsByName(
            @NotBlank(message = "{validation.not_blank_message}") String name
    );

    /**
     * Deletes an equipment entity by its ID.
     *
     * @param id the ID of the equipment entity to delete
     */
    void deleteEquipmentById(
            @NotNull(message = "{validation.id.not_null_required.message}")
            @Min(value = 1, message = "{validation.min_value.message}") Long id
    );

    /**
     * Updates an existing equipment entity.
     *
     * @param equipment the equipment entity to update
     */
    @Transactional
    void updateEquipment(@Valid EquipmentEntity equipment);

    /**
     * Updates the name or image URL of an equipment entity with the given ID.
     *
     * @param id the ID of the equipment entity to update
     * @param name the new name to set
     * @param imageUrl the new image URL to set
     * @return true if the update was successful, false otherwise
     */
    boolean updateEquipment(
            @NotNull Long id,
            @Size(min = 3, max = 25, message = "{validation.size.message}") String name,
            @URL(message = "{validation.ul") String imageUrl
    );

}
