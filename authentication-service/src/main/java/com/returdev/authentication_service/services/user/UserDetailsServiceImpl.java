package com.returdev.authentication_service.services.user;

import com.returdev.authentication_service.client.user.UserFeignClient;
import com.returdev.authentication_service.client.user.model.UserClientModel;
import com.returdev.authentication_service.mapper.user.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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

    /**
     * Feign client for interacting with the user service.
     * Used to retrieve user information by email.
     */
    private final UserFeignClient userClient;

    /**
     * Mapper for converting user client models to Spring Security user details.
     */
    private final UserMapper userMapper;

    /**
     * Loads the user details by their email address.
     * This method fetches the user data from the user service and maps it to a {@link UserDetails} object.
     *
     * @param email the email address of the user to load
     * @return a {@link UserDetails} object containing the user's information
     * @throws UsernameNotFoundException if the user is not found
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserClientModel user = userClient.getUserByEmail(email);
        return userMapper.userClientModelToUserDetails(user);
    }
}
