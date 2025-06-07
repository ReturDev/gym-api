package com.returdev.authentication_service.mapper.user;

import com.returdev.authentication_service.client.user.model.UserClientModel;
import com.returdev.authentication_service.model.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the UserMapper interface.
 * This class provides the functionality to map user client models to user details.
 * It is annotated with `@Component` to indicate that it is a Spring-managed component.
 */
@Component
public class UserMapperImpl implements UserMapper {

    /**
     * Maps a `UserClientModel` object to a `UserDetails` object.
     * This method transforms the external user client model into the internal user details representation.
     *
     * @param userClientModel the `UserClientModel` object containing user data from external services
     * @return a `UserDetails` object containing the mapped user data
     */
    @Override
    public UserDetails userClientModelToUserDetails(UserClientModel userClientModel) {
        return new UserDetails(
                userClientModel.email(),
                userClientModel.password(),
                userClientModel.isEnabled(),
                true,
                true,
                userClientModel.isEnabled(),
                generateAuthorities(userClientModel),
                userClientModel.id(),
                userClientModel.username(),
                userClientModel.name(),
                userClientModel.surnames(),
                userClientModel.userRole().name(),
                userClientModel.userRole().permissions()
        );
    }

    /**
     * Generates a list of `SimpleGrantedAuthority` objects based on the user's role and permissions.
     * This method creates authorities for the user's role and adds additional authorities for each permission.
     *
     * @param userClientModel the `UserClientModel` object containing user role and permissions
     * @return a list of `SimpleGrantedAuthority` objects representing the user's authorities
     */
    private List<SimpleGrantedAuthority> generateAuthorities(UserClientModel userClientModel) {

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        // Add the role as an authority
        authorities.add(new SimpleGrantedAuthority("ROLE_" + userClientModel.userRole().name()));

        // Add each permission as an authority
        userClientModel.userRole().permissions().forEach(p -> authorities.add(new SimpleGrantedAuthority(p)));

        return authorities;
    }
}
