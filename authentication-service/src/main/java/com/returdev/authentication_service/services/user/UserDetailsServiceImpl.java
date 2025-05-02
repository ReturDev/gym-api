package com.returdev.authentication_service.services.user;

import com.returdev.authentication_service.client.user.UserFeignClient;
import com.returdev.authentication_service.client.user.model.UserClientModel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service implementation of {@link UserDetailsService} for loading user-specific data.
 * This class is annotated with `@Service` to indicate that it is a Spring-managed service
 * and uses `@RequiredArgsConstructor` to generate a constructor for its final fields.
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserFeignClient userClient;

    /**
     * Loads the user's details by their email address.
     * Fetches the user data from the {@link UserFeignClient} and maps the user's roles
     * and permissions to Spring Security's {@link SimpleGrantedAuthority}.
     *
     * @param email The email address of the user to load.
     * @return A {@link UserDetails} object containing the user's information.
     * @throws UsernameNotFoundException if the user cannot be found.
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserClientModel user = userClient.getUserByEmail(email);

        List<SimpleGrantedAuthority> authorities = user.userRole().permissions().stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toCollection(ArrayList::new));

        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.userRole().name()));

        return new User(
                user.email(),
                user.password(),
                user.isEnabled(),
                true,
                true,
                user.isEnabled(),
                authorities
        );
    }
}
