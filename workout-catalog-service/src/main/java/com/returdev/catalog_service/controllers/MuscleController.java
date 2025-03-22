package com.returdev.catalog_service.controllers;

import com.returdev.catalog_service.dtos.content.ContentResponseDTO;
import com.returdev.catalog_service.dtos.muscle.MuscleRequestDTO;
import com.returdev.catalog_service.dtos.muscle.MuscleResponseDTO;
import com.returdev.catalog_service.enums.MuscularGroup;
import com.returdev.catalog_service.mappers.MuscleMapper;
import com.returdev.catalog_service.services.muscle.MuscleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * REST controller for managing muscle entities.
 * This controller provides endpoints for CRUD operations and custom queries related to muscle entities.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/muscles")
public class MuscleController {

    private final MuscleService muscleService;
    private final MuscleMapper muscleMapper;

    /**
     * Saves a new muscle entity.
     *
     * @param muscleRequestDTO the muscle data to save
     * @return the saved muscle response DTO
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContentResponseDTO<MuscleResponseDTO> saveMuscle(@Valid @RequestBody MuscleRequestDTO muscleRequestDTO) {
        return muscleMapper.toContentResponse(
                muscleService.saveMuscle(
                        muscleMapper.mapToEntity(muscleRequestDTO)
                )
        );
    }

    /**
     * Retrieves a muscle entity by its ID.
     *
     * @param id the ID of the muscle entity
     * @return the muscle response DTO
     */
    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ContentResponseDTO<MuscleResponseDTO> getMuscleById(@PathVariable("id") Long id) {
        return muscleMapper.toContentResponse(
                muscleService.getMuscleById(id)
        );
    }

    /**
     * Retrieves a muscle entity by its name.
     *
     * @param name the name of the muscle entity
     * @return the muscle response DTO
     */
    @GetMapping("/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public ContentResponseDTO<MuscleResponseDTO> getMuscleByName(@PathVariable("name") String name) {
        return muscleMapper.toContentResponse(
                muscleService.getMuscleByName(name)
        );
    }

    /**
     * Retrieves muscle entities by their muscular group.
     *
     * @param muscularGroup the muscular group of the muscles
     * @return a content response DTO containing a list of muscle response DTOs
     */
    @GetMapping("/muscularGroup/{group}")
    @ResponseStatus(HttpStatus.OK)
    public ContentResponseDTO<List<MuscleResponseDTO>> getMusclesOfMuscularGroup(@PathVariable("group") MuscularGroup muscularGroup) {
        return muscleMapper.toContentResponse(muscleService.getMusclesOfMuscleGroup(muscularGroup));
    }

    /**
     * Retrieves all muscle entities.
     *
     * @return a content response DTO containing a list of all muscle response DTOs
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ContentResponseDTO<List<MuscleResponseDTO>> getAllMuscles() {
        return muscleMapper.toContentResponse(muscleService.getAllMuscles());
    }

    /**
     * Deletes a muscle entity by its ID.
     *
     * @param id the ID of the muscle entity to delete
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMuscleById(@PathVariable("id") Long id) {
        muscleService.deleteMuscleById(id);
    }

}
