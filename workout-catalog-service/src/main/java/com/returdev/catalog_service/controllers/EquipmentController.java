package com.returdev.catalog_service.controllers;


import com.returdev.catalog_service.dtos.equipment.EquipmentRequestDTO;
import com.returdev.catalog_service.dtos.equipment.EquipmentResponseDTO;
import com.returdev.catalog_service.mappers.EquipmentMapper;
import com.returdev.catalog_service.services.equipment.EquipmentService;
import com.returdev.utils_library.dtos.content.ContentResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing equipment entities.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/equipments")
public class EquipmentController {

    private final EquipmentService equipmentService;
    private final EquipmentMapper equipmentMapper;

    /**
     * Saves a new equipment entity.
     *
     * @param newEquipment the equipment data to save
     * @return the saved equipment response DTO
     */
    @PreAuthorize("hasAuthority('EXERCISE_SYSTEM_WRITE')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContentResponseDTO<EquipmentResponseDTO> saveEquipment(@Valid @RequestBody EquipmentRequestDTO newEquipment) {
        return equipmentMapper.toContentResponse(
                equipmentService.saveEquipment(
                        equipmentMapper.mapToEntity(newEquipment)
                )
        );
    }

    /**
     * Retrieves all equipment entities.
     *
     * @return a content response DTO containing a list of all equipment response DTOs
     */
    @PreAuthorize("hasAuthority('EXERCISE_SYSTEM_READ')")
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ContentResponseDTO<List<EquipmentResponseDTO>> getAllEquipments() {
        return equipmentMapper.toContentResponse(equipmentService.getAllEquipments());
    }

    /**
     * Retrieves an equipment entity by its ID.
     *
     * @param id the ID of the equipment entity
     * @return the equipment response DTO
     */
    @PreAuthorize("hasAuthority('EXERCISE_SYSTEM_READ')")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ContentResponseDTO<EquipmentResponseDTO> getEquipmentById(@PathVariable("id") Long id) {
        return equipmentMapper.toContentResponse(
                equipmentService.getEquipmentById(id)
        );
    }

    /**
     * Retrieves an equipment entity by its name.
     *
     * @param name the name of the equipment entity
     * @return the equipment response DTO
     */
    @PreAuthorize("hasAuthority('EXERCISE_SYSTEM_READ')")
    @GetMapping("/by-name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public ContentResponseDTO<EquipmentResponseDTO> getEquipmentByName(@PathVariable("name") String name) {
        return equipmentMapper.toContentResponse(
                equipmentService.getEquipmentByName(name)
        );
    }

    /**
     * Checks if an equipment entity exists by its name.
     *
     * @param name the name of the equipment entity
     * @return a ResponseEntity indicating the result of the existence check
     */
    @PreAuthorize("hasAuthority('EXERCISE_SYSTEM_READ')")
    @GetMapping("/by-name/{name}/exists")
    public ResponseEntity<Void> equipmentExistsByName(@PathVariable("name") String name) {
        if (equipmentService.existsByName(name)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.notFound().build();
    }

    /**
     * Deletes an equipment entity by its ID.
     *
     * @param id the ID of the equipment entity to delete
     */
    @PreAuthorize("hasAuthority('EXERCISE_SYSTEM_WRITE')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEquipmentById(@PathVariable("id") Long id) {
        equipmentService.deleteEquipmentById(id);
    }

    /**
     * Updates an existing equipment entity.
     *
     * @param equipmentRequestDTO the equipment data to update
     */
    @PreAuthorize("hasAuthority('EXERCISE_SYSTEM_WRITE')")
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateEquipment(@Valid @RequestBody EquipmentRequestDTO equipmentRequestDTO) {
        equipmentService.updateEquipment(
                equipmentMapper.mapToEntity(equipmentRequestDTO)
        );
    }

    /**
     * Updates the name of an existing equipment entity.
     *
     * @param id the ID of the equipment entity to update
     * @param newName the new name of the equipment entity
     * @return a response entity indicating the result of the operation
     */
    @PreAuthorize("hasAuthority('EXERCISE_SYSTEM_WRITE')")
    @PatchMapping("/{id}/name")
    public ResponseEntity<Void> updateEquipmentName(@PathVariable("id") Long id, @RequestBody String newName) {
        if (equipmentService.updateEquipment(id, newName, null)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
    }

    /**
     * Updates the image URL of an existing equipment entity.
     *
     * @param id the ID of the equipment entity to update
     * @param newImageUrl the new image URL of the equipment entity
     * @return a response entity indicating the result of the operation
     */
    @PreAuthorize("hasAuthority('EXERCISE_SYSTEM_WRITE')")
    @PatchMapping("/{id}/imageUrl")
    public ResponseEntity<Void> updateEquipmentImageUrl(@PathVariable("id") Long id, @RequestBody String newImageUrl) {
        if (equipmentService.updateEquipment(id, null, newImageUrl)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
    }
}