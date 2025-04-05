package com.returdev.catalog_service.controllers;

import com.returdev.catalog_service.annotations.HasExerciseSystemReadPermission;
import com.returdev.catalog_service.annotations.HasExerciseSystemWritePermission;
import com.returdev.catalog_service.dtos.exercise.ExerciseRequestDTO;
import com.returdev.catalog_service.dtos.exercise.ExerciseResponseDTO;
import com.returdev.catalog_service.entities.ExerciseEntity;
import com.returdev.catalog_service.enums.MuscleActivationLevel;
import com.returdev.catalog_service.mappers.ExerciseMapper;
import com.returdev.catalog_service.services.exercise.ExerciseService;
import com.returdev.utils_library.dtos.content.ContentResponseDTO;
import com.returdev.utils_library.dtos.pagination.PaginationRequestDTO;
import com.returdev.utils_library.dtos.pagination.PaginationResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing exercise entities.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;
    private final ExerciseMapper exerciseMapper;

    /**
     * Retrieves an exercise entity by its ID.
     *
     * @param exerciseId the ID of the exercise entity
     * @return the exercise response DTO
     */
    @HasExerciseSystemReadPermission
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ExerciseResponseDTO getExerciseById(@PathVariable("id") Long exerciseId) {
        return exerciseMapper.mapToResponseDto(
                exerciseService.getExerciseEntityById(exerciseId)
        );
    }

    /**
     * Retrieves all exercise entities with pagination.
     *
     * @param includeInvisible whether to include invisible exercises
     * @param paginationRequestDTO the pagination request DTO
     * @return a pagination response DTO containing a list of exercise response DTOs
     */
    @HasExerciseSystemReadPermission
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PaginationResponseDTO<ExerciseResponseDTO> getAllExercises(
            @RequestParam(value = "show-invisible", defaultValue = "false") boolean includeInvisible,
            PaginationRequestDTO paginationRequestDTO
    ) {

        if (includeInvisible){
            checkInvisiblePermission();
        }

        return exerciseMapper.mapToPaginationResponseDTO(
                exerciseService.getAllExercises(
                        includeInvisible,
                        exerciseMapper.mapToPageable(paginationRequestDTO)
                )
        );
    }

    /**
     * Retrieves exercise entities by name containing a specified string with pagination.
     *
     * @param name the name to search for
     * @param includeInvisible whether to include invisible exercises
     * @param pagination the pagination request DTO
     * @return a pagination response DTO containing a list of exercise response DTOs
     */
    @HasExerciseSystemReadPermission
    @GetMapping("/by-name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public PaginationResponseDTO<ExerciseResponseDTO> getExercisesByNameContaining(
            @PathVariable("name") String name,
            @RequestParam(name = "show-invisible", defaultValue = "false") boolean includeInvisible,
            PaginationRequestDTO pagination
    ) {

        if (includeInvisible){
            checkInvisiblePermission();
        }

        return exerciseMapper.mapToPaginationResponseDTO(
                exerciseService.getExercisesByNameContaining(
                        name,
                        includeInvisible,
                        exerciseMapper.mapToPageable(pagination)
                )
        );
    }

    /**
     * Retrieves exercise entities by muscle name with pagination.
     *
     * @param muscleName the muscle name to search for
     * @param includeInvisible whether to include invisible exercises
     * @param pagination the pagination request DTO
     * @return a pagination response DTO containing a list of exercise response DTOs
     */
    @HasExerciseSystemReadPermission
    @GetMapping("/by-muscle/{muscleName}")
    @ResponseStatus(HttpStatus.OK)
    public PaginationResponseDTO<ExerciseResponseDTO> getExercisesByMuscleName(
            @PathVariable("muscleName") String muscleName,
            @RequestParam(name = "show-invisible", defaultValue = "false") boolean includeInvisible,
            PaginationRequestDTO pagination
    ) {

        if (includeInvisible){
            checkInvisiblePermission();
        }

        return exerciseMapper. mapToPaginationResponseDTO(
                exerciseService.getExercisesByMuscleName(
                        muscleName,
                        includeInvisible,
                        exerciseMapper.mapToPageable(pagination)
                )
        );
    }

    /**
     * Retrieves exercise entities by muscle name and activation level with pagination.
     *
     * @param muscleName the muscle name to search for
     * @param activationLevel the activation level to search for
     * @param includeInvisible whether to include invisible exercises
     * @param pagination the pagination request DTO
     * @return a pagination response DTO containing a list of exercise response DTOs
     */
    @HasExerciseSystemReadPermission
    @GetMapping("/by-muscle/{muscleName}/by-activation/{activationLevel}")
    @ResponseStatus(HttpStatus.OK)
    public PaginationResponseDTO<ExerciseResponseDTO> getExercisesByMuscleAndActivationLevel(
            @PathVariable("muscleName") String muscleName,
            @PathVariable("activationLevel") MuscleActivationLevel activationLevel,
            @RequestParam(value = "show-invisible", defaultValue = "false") boolean includeInvisible,
            PaginationRequestDTO pagination
    ) {

        if (includeInvisible){
            checkInvisiblePermission();
        }

        return exerciseMapper.mapToPaginationResponseDTO(
                exerciseService.getExercisesByMuscleNameAndActivationLevel(
                        muscleName,
                        activationLevel,
                        includeInvisible,
                        exerciseMapper.mapToPageable(pagination)
                )
        );
    }

    /**
     * Saves a new exercise entity.
     *
     * @param exercise the exercise data to save
     * @return the saved exercise response DTO
     */
    @HasExerciseSystemWritePermission
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContentResponseDTO<ExerciseResponseDTO> saveExercise(
            @RequestBody @Valid ExerciseRequestDTO exercise
    ) {
        return exerciseMapper.toContentResponse(
                exerciseService.saveExercise(
                        exerciseMapper.mapToEntity(exercise)
                )
        );
    }

    /**
     * Updates an existing exercise entity.
     *
     * @param exercise the exercise data to update
     * @return a response entity indicating the result of the operation
     */
    @HasExerciseSystemWritePermission
    @PutMapping
    public ResponseEntity<Void> updateExercise(
            @RequestBody ExerciseRequestDTO exercise
    ) {
        ExerciseEntity exerciseEntity = exerciseMapper.mapToEntity(exercise);
        boolean updated = exerciseService.updateExercise(
                exerciseEntity.getId(),
                exerciseEntity.getDescription(),
                exerciseEntity.isBenchRequired(),
                exerciseEntity.getMusclesInvolved(),
                exerciseEntity.getExerciseImageUrl(),
                exerciseEntity.getExerciseVideoUrl(),
                exerciseEntity.isVisible()
        );

        if (updated) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
    }

    @PreAuthorize("hasAuthority('EXERCISE_SYSTEM_READ_INVISIBLE')")
    private void checkInvisiblePermission(){}

}
