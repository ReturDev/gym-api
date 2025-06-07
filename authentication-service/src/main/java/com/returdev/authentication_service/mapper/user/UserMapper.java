package com.returdev.authentication_service.mapper.user;

import com.returdev.authentication_service.client.user.model.UserClientModel;
import com.returdev.authentication_service.model.UserDetails;

/**
 * Mapper interface for converting user client models to user details.
 * This interface defines methods for mapping between the `UserClientModel`
 * received from external services and the `UserDetails` used within the application.
 */
public interface UserMapper {

    /**
     * Maps a `UserClientModel` object to a `UserDetails` object.
     * This method is used to transform the external user client model
     * into the internal user details representation.
     *
     * @param userClientModel the `UserClientModel` object containing user data from external services
     * @return a `UserDetails` object containing the mapped user data
     */
    UserDetails userClientModelToUserDetails(UserClientModel userClientModel);

}
