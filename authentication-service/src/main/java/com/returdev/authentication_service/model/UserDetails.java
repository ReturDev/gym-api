package com.returdev.authentication_service.model;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * Custom implementation of the `User` class from Spring Security.
 * This class represents detailed user information, including additional fields
 * such as ID, account name, name, surnames, role, and permissions.
 * It is used for authentication and authorization purposes within the application.
 */
@Getter
public class UserDetails extends User {


    private final UUID id;
    private final String accountName;
    private final String name;
    private final String surnames;
    private final String role;
    private final List<String> permissions;

    /**
     * Constructs a new `UserDetails` object with the specified parameters.
     *
     * @param username the username of the user
     * @param password the password of the user
     * @param enabled whether the user is enabled
     * @param accountNonExpired whether the user's account is non-expired
     * @param credentialsNonExpired whether the user's credentials are non-expired
     * @param accountNonLocked whether the user's account is non-locked
     * @param authorities the collection of granted authorities for the user
     * @param id the unique identifier of the user
     * @param accountName the account name associated with the user
     * @param name the first name of the user
     * @param surnames the surnames of the user
     * @param role the role assigned to the user
     * @param permissions the list of permissions granted to the user
     */
    public UserDetails(
            String username,
            String password,
            boolean enabled,
            boolean accountNonExpired,
            boolean credentialsNonExpired,
            boolean accountNonLocked,
            Collection<? extends GrantedAuthority> authorities,
            UUID id, String accountName,
            String name,
            String surnames,
            String role,
            List<String> permissions
    ) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.id = id;
        this.accountName = accountName;
        this.name = name;
        this.surnames = surnames;
        this.role = role;
        this.permissions = permissions;
    }

}
