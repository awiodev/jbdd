package io.github.awiodev.jbdd.examples.restassured.steps;

import io.github.awiodev.jbdd.examples.restassured.model.User;
import io.restassured.response.Response;

/**
 * Users service API business actions.
 */
public interface UserApiSteps {

    /**
     * Responsible for creating user in the User service.
     *
     * @param payload for user payload
     * @return updated user object
     */
    Response whenCreatesUser(User payload);

    /**
     * Responsible for retrieving user from the User service.
     *
     * @param userId for existing user id.
     * @return user object
     */
    Response whenRetrievesUser(String userId);

    /**
     * Verification of user creation response.
     *
     * @param response as a raw response object
     * @return deserialized User
     */
    User thenUserIsCreatedSuccessfully(Response response);

    /**
     * Verification of user data retrieval
     * @param response as raw response object
     * @return deserialized user
     */
    User thenUserDataIsRetrieved(Response response);
}
