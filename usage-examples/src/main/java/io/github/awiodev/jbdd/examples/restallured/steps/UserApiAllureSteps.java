package io.github.awiodev.jbdd.examples.restallured.steps;

import io.github.awiodev.jbdd.examples.restassured.model.User;
import io.github.awiodev.jbdd.examples.restassured.steps.UserApiSteps;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class UserApiAllureSteps implements UserApiSteps {

    private final UserApiSteps userApiSteps;

    private UserApiAllureSteps(UserApiSteps userApiSteps) {
        this.userApiSteps = userApiSteps;
    }

    @Step("when creates user")
    @Override
    public Response whenCreatesUser(User payload) {
        return userApiSteps.whenCreatesUser(payload);
    }

    @Step("then retrieves user")
    @Override
    public Response whenRetrievesUser(String userId) {
        return userApiSteps.whenRetrievesUser(userId);
    }

    @Step("then user is created successfully")
    @Override
    public User thenUserIsCreatedSuccessfully(Response response) {
        return userApiSteps.thenUserIsCreatedSuccessfully(response);
    }

    @Step("then user data is retrieved")
    @Override
    public User thenUserDataIsRetrieved(Response response) {
        return userApiSteps.thenUserDataIsRetrieved(response);
    }
}
