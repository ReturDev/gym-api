package com.returdev.catalog_service.controllers;

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
     * @return the saved muscle data
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MuscleResponseDTO saveMuscle(@Valid @RequestBody MuscleRequestDTO muscleRequestDTO) {
        return muscleMapper.toResponseDto(
                muscleService.saveMuscle(
                        muscleMapper.toEntity(muscleRequestDTO)
                )
        );
    }

    /**
     * Retrieves a muscle entity by its ID.
     *
     * @param id the ID of the muscle entity
     * @return the muscle data with the specified ID
     */
    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MuscleResponseDTO getMuscleById(@PathVariable("id") Long id) {
        return muscleMapper.toResponseDto(
                muscleService.getMuscleById(id)
        );
    }

    /**
     * Retrieves a muscle entity by its name.
     *
     * @param name the name of the muscle entity
     * @return the muscle data with the specified name
     */
    @GetMapping("/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public MuscleResponseDTO getMuscleByName(@PathVariable("name") String name) {
        return muscleMapper.toResponseDto(
                muscleService.getMuscleByName(name)
        );
    }

    /**
     * Retrieves muscle entities of a specific muscular group.
     *
     * @param muscularGroup the muscular group of the muscle entities
     * @return a list of muscle data belonging to the specified muscular group
     */
    @GetMapping("/muscularGroup/{group}")
    @ResponseStatus(HttpStatus.OK)
    public List<MuscleResponseDTO> getMusclesOfMuscularGroup(@PathVariable("group") MuscularGroup muscularGroup) {
        return muscleService.getMusclesOfMuscleGroup(muscularGroup).stream().map(muscleMapper::toResponseDto).toList();
    }

    /**
     * Retrieves all muscle entities.
     *
     * @return a list of all muscle data
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MuscleResponseDTO> getAllMuscles() {
        return muscleService.getAllMuscles().stream().map(muscleMapper::toResponseDto).toList();
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
