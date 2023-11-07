package io.github.awiodev.jbdd.examples.restassured.steps;

import com.github.javafaker.Faker;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;
import io.github.awiodev.jbdd.core.definition.Steps;
import io.github.awiodev.jbdd.examples.restassured.model.User;
import java.util.UUID;

/**
 * Steps class created for the purpose of examples.
 * It is responsible for setting up fake user service responses.
 */
public class FakeUserServiceSteps implements Steps {

    private static final String POST_PATH = "/user";
    private static final String GET_PATH = "/user/{id}";

    private final WireMockServer wireMockServer;
    private final Faker faker;

    String userResponse = "{ \"id\":\"%s\", \"name\":\"%s\", \"email\": \"%s\" }";

    public FakeUserServiceSteps(WireMockServer wireMockServer, Faker faker) {
        this.wireMockServer = wireMockServer;
        this.faker = faker;
    }

    /**
     * Generates user id and mocks a response
     *
     * @param user as user creation payload
     */
    public StubMapping givenUserServiceAcceptsUser(User user) {
        runServer();
        String id = UUID.randomUUID().toString();
        return wireMockServer.stubFor(
            WireMock.post(POST_PATH)
                .withRequestBody(WireMock.containing(user.getEmail()))
                .willReturn(WireMock.aResponse()
                    .withStatus(201)
                    .withHeader("Content-Type", "application/json")
                    .withBody(prepareUserResponse(id, user.getName(), user.getEmail()))
                )
        );
    }

    public StubMapping givenUserServiceReturnsUser(String userId) {
        runServer();
        String name = faker.name().fullName();
        String email = faker.internet().emailAddress();

        return wireMockServer.stubFor(WireMock.get(GET_PATH.replace("{id}", userId))
            .willReturn(WireMock.aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody(prepareUserResponse(userId, name, email))
            )
        );
    }

    public void destroy() {
        wireMockServer.stop();
    }

    private String prepareUserResponse(String id, String name, String email) {
        return String.format(userResponse, id, name, email);
    }

    private void runServer() {
        if (!wireMockServer.isRunning()) {
            wireMockServer.start();
        }
    }
}
