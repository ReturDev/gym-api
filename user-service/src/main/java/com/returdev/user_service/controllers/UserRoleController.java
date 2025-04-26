package com.returdev.user_service.controllers;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.returdev.user_service.dtos.role.UserRoleRequestDTO;
import com.returdev.user_service.dtos.role.UserRoleResponseDTO;
import com.returdev.user_service.entities.UserPermissionEntity;
import com.returdev.user_service.mappers.UserRoleMapper;
import com.returdev.user_service.services.role.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing user roles.
 * Provides endpoints for retrieving, modifying, and saving user roles.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/user-role")
public class UserRoleController {

    private final UserRoleService userRoleService;
    private final UserRoleMapper userRoleMapper;

    /**
     * Retrieves a user role by its name.
     *
     * @param name the name of the user role
     * @return the user role response DTO
     */
    @PreAuthorize("hasAuthority('MANAGE_USER_ROLES')")
    @GetMapping("/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public UserRoleResponseDTO getRoleByName(@PathVariable("name") String name) {
        return userRoleMapper.mapToResponseDto(
                userRoleService.getRoleByName(name)
        );
    }

    /**
     * Retrieves a user role by its ID.
     *
     * @param id the ID of the user role
     * @return the user role response DTO
     */
    @PreAuthorize("hasAuthority('MANAGE_USER_ROLES')")
    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserRoleResponseDTO getRoleById(@PathVariable("id") Long id) {
        return userRoleMapper.mapToResponseDto(
                userRoleService.getRoleById(id)
        );
    }

    /**
     * Retrieves all user roles.
     *
     * @return a list of user role response DTOs
     */
    @PreAuthorize("hasAuthority('MANAGE_USER_ROLES')")
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<UserRoleResponseDTO> getAllRoles() {
        return userRoleService.getAllRoles().stream().map(userRoleMapper::mapToResponseDto).toList();
    }

    /**
     * Modifies the permissions of a user role.
     *
     * @param id the ID of the user role
     * @param permissionIds a list of permission IDs to assign to the role
     */
    @PreAuthorize("hasAuthority('MANAGE_USER_ROLES')")
    @PatchMapping("/{id}/permissions")
    @ResponseStatus(HttpStatus.OK)
    public void changePermissions(
            @PathVariable("id") Long id,
            @RequestBody() @JsonProperty("permission-ids") List<Long> permissionIds
    ) {
        userRoleService.modifyRolePermissions(
                id,
                permissionIds.stream().map(it -> new UserPermissionEntity(it, null)).toList()
        );
    }

    /**
     * Saves a new user role.
     *
     * @param role the user role request DTO containing role details
     * @return the saved user role response DTO
     */
    @PreAuthorize("hasAuthority('MANAGE_USER_ROLES')")
    @PostMapping()
    public UserRoleResponseDTO saveRole(@RequestBody UserRoleRequestDTO role) {
        return userRoleMapper.mapToResponseDto(
                userRoleService.saveRole(
                        userRoleMapper.mapToEntity(role)
                )
        );
    }

}
