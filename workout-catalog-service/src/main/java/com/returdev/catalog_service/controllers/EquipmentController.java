package com.returdev.catalog_service.controllers;


import com.returdev.catalog_service.dtos.equipment.EquipmentRequestDTO;
import com.returdev.catalog_service.dtos.equipment.EquipmentResponseDTO;
import com.returdev.catalog_service.mappers.EquipmentMapper;
import com.returdev.catalog_service.services.equipment.EquipmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
     * Creates a new equipment entity.
     *
     * @param newEquipment the equipment data to create
     * @return the created equipment response DTO
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EquipmentResponseDTO saveEquipment(@Valid @RequestBody EquipmentRequestDTO newEquipment) {
        return equipmentMapper.toResponseDto(
                equipmentService.saveEquipment(
                        equipmentMapper.toEntity(newEquipment)
                )
        );
    }

    /**
     * Retrieves all equipment entities.
     *
     * @return a list of all equipment response DTOs
     */
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<EquipmentResponseDTO> getAllEquipments() {
        return equipmentService.getAllEquipments().stream().map(equipmentMapper::toResponseDto).toList();
    }

    /**
     * Retrieves an equipment entity by its ID.
     *
     * @param id the ID of the equipment entity
     * @return the equipment response DTO
     */
    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EquipmentResponseDTO getEquipmentById(@PathVariable("id") Long id) {
        return equipmentMapper.toResponseDto(
                equipmentService.getEquipmentById(id)
        );
    }

    /**
     * Retrieves an equipment entity by its name.
     *
     * @param name the name of the equipment entity
     * @return the equipment response DTO
     */
    @GetMapping("/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public EquipmentResponseDTO getEquipmentByName(@PathVariable("name") String name) {
        return equipmentMapper.toResponseDto(
                equipmentService.getEquipmentByName(name)
        );
    }


    /**
     * Checks if an equipment entity exists by its name.
     *
     * @param name the name of the equipment entity
     * @return a ResponseEntity indicating the result of the existence check
     */
    @GetMapping("/exists/{name}")
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
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateEquipment(@Valid @RequestBody EquipmentRequestDTO equipmentRequestDTO) {
        equipmentService.updateEquipment(
                equipmentMapper.toEntity(equipmentRequestDTO)
        );
    }

    /**
     * Updates the name of an existing equipment entity.
     *
     * @param id the ID of the equipment entity to update
     * @param newName the new name of the equipment entity
     * @return a response entity indicating the result of the operation
     */
    @PatchMapping("/name/{id}")
    public ResponseEntity<Void> updateEquipmentName(@PathVariable("id") Long id, @RequestBody String newName) {
        if (equipmentService.updateEquipment(id, newName, null)) {
            return ResponseEntity.ok().build();
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
    @PatchMapping("imageUrl/{id}")
    public ResponseEntity<Void> updateEquipmentImageUrl(@PathVariable("id") Long id, @RequestBody String newImageUrl) {
        if (equipmentService.updateEquipment(id, null, newImageUrl)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
    }
}
