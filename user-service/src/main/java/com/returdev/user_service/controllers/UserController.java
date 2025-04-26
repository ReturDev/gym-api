package com.returdev.user_service.controllers;

import com.returdev.user_service.dtos.password.ChangePasswordRequestDTO;
import com.returdev.user_service.dtos.user.UserRequestDTO;
import com.returdev.user_service.dtos.user.UserResponseDTO;
import com.returdev.user_service.enums.UserPermission;
import com.returdev.user_service.mappers.UserMapper;
import com.returdev.user_service.services.user.UserService;
import com.returdev.utils_library.dtos.pagination.PaginationRequestDTO;
import com.returdev.utils_library.dtos.pagination.PaginationResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * REST controller for managing user-related operations.
 * Provides endpoints for retrieving, updating, and managing user entities.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    /**
     * Retrieves a user by their email for internal use.
     *
     * @param email the email of the user
     * @return the user response DTO
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/internal/email/{email}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDTO internalGetUserByEmail(@PathVariable("email") String email){
        return userMapper.mapToResponseDto(
                userService.getUserByEmail(email, true)
        );
    }

    /**
     * Retrieves a user by their email.
     *
     * @param email the email of the user
     * @return the user response DTO
     */
    @PreAuthorize("hasAuthority('USER_ACCOUNT_BASICS')")
    @GetMapping("/email/{email}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDTO getUserByEmail(@PathVariable("email") String email){
        return userMapper.mapToResponseDto(
                userService.getUserByEmail(email, false)
        );
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id the UUID of the user
     * @return the user response DTO
     */
    @PreAuthorize("hasAuthority('USER_ACCOUNT_BASICS')")
    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDTO getUserById(@PathVariable("id") UUID id) {
        return userMapper.mapToResponseDto(
                userService.getUserById(id)
        );
    }

    /**
     * Retrieves users by their role name with pagination.
     *
     * @param roleName the name of the role
     * @param pagination the pagination request DTO
     * @return a pagination response DTO containing user response DTOs
     */
    @PreAuthorize("hasAuthority('MANAGE_USERS')")
    @GetMapping("/role/{role-name}")
    @ResponseStatus(HttpStatus.OK)
    public PaginationResponseDTO<UserResponseDTO> getUsersByRoleName(
            @PathVariable("role-name") String roleName,
            PaginationRequestDTO pagination
    ) {
        return userMapper.mapToPaginationResponseDTO(
                userService.getUsersByRoleName(
                        roleName,
                        userMapper.mapToPageable(pagination)
                )
        );
    }

    /**
     * Updates a user's password by their email.
     *
     * @param email the email of the user
     * @param changePasswordRequest the request DTO containing old and new passwords
     */
    @PreAuthorize("hasAuthority('USER_ACCOUNT_BASICS')")
    @PatchMapping("/{email}/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePasswordByEmail(
            @PathVariable("email") String email,
            @RequestBody ChangePasswordRequestDTO changePasswordRequest
    ) {
        userService.updatePasswordByEmail(
                email,
                changePasswordRequest.oldPassword(),
                changePasswordRequest.newPassword()
        );
    }

    /**
     * Updates a user's information by their ID.
     *
     * @param id the UUID of the user
     * @param userRequest the request DTO containing updated user information
     */
    @PreAuthorize("hasAuthority('USER_ACCOUNT_BASICS')")
    @PutMapping("/{id}/user-info")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUserInfo(
            @PathVariable("id") UUID id,
            @RequestBody UserRequestDTO userRequest
    ){
        userService.updateUserInfo(
                id,
                userRequest.username(),
                userRequest.name(),
                userRequest.surnames()
        );
    }

    /**
     * Changes a user's role by their ID.
     *
     * @param id the UUID of the user
     * @param role the new role to assign
     */
    @PreAuthorize("hasAuthority('MANAGE_USER_ROLES')")
    @PatchMapping("/{id}/role")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changeUserRole(@PathVariable("id") UUID id, @RequestBody() String role) {
        userService.changeRole(id, role);
    }

    /**
     * Verifies a user by their ID.
     *
     * @param id the UUID of the user
     */
    @PreAuthorize("hasAnyAuthority('USER_ACCOUNT_BASICS', 'MANAGE_USERS)")
    @PatchMapping("/{id}/verify")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void verifyUser(@PathVariable("id") UUID id) {
        userService.verifyUser(id);
    }

    /**
     * Disables a user by their ID.
     *
     * @param id the UUID of the user
     */
    @PreAuthorize("hasAuthority('MANAGE_USERS')")
    @PatchMapping("/{id}/disable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disableUser(@PathVariable("id") UUID id) {
        userService.disableUser(id);
    }

    /**
     * Enables a user by their ID.
     *
     * @param id the UUID of the user
     */
    @PreAuthorize("hasAuthority('MANAGE_USERS')")
    @PatchMapping("/{id}/enable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void enableUser(@PathVariable("id") UUID id) {
        userService.enableUser(id);
    }

    /**
     * Saves a new user.
     *
     * @param user the request DTO containing user information
     * @return the saved user response DTO
     */
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO saveUser(
            @RequestBody UserRequestDTO user
    ) {
        return userMapper.mapToResponseDto(
                userService.saveUser(
                        userMapper.mapToEntity(user)
                )
        );
    }

    /**
     * Deletes a user by their ID.
     *
     * @param id the UUID of the user
     * @param jwt the JWT of the authenticated user
     */
    @PreAuthorize("hasAnyAuthority('USER_ACCOUNT_BASICS', 'MANAGE_USERS')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void deleteUser(
            @PathVariable("id") UUID id,
            @AuthenticationPrincipal Jwt jwt
    ) {
        UUID tokenUserId = UUID.fromString(
                jwt.getClaimAsString("id")
        );

        boolean hasPermissions = jwt.getClaimAsStringList("permissions").contains(UserPermission.MANAGE_USERS.name());

        if (tokenUserId != id || !hasPermissions) {
            throw new AccessDeniedException("");
        }

        userService.deleteUser(id);
    }

}
