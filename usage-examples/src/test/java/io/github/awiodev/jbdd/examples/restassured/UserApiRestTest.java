package io.github.awiodev.jbdd.examples.restassured;

import com.github.javafaker.Faker;
import com.github.tomakehurst.wiremock.WireMockServer;
import io.github.awiodev.jbdd.core.definition.JBddRun;
import io.github.awiodev.jbdd.core.impl.JBddStandardContextFactory;
import io.github.awiodev.jbdd.core.impl.JBddStandardSteps;
import io.github.awiodev.jbdd.examples.restassured.custom.JBddUserApiRun;
import io.github.awiodev.jbdd.examples.restassured.model.User;
import io.github.awiodev.jbdd.examples.restassured.steps.FakeUserServiceSteps;
import io.github.awiodev.jbdd.examples.restassured.steps.UserApiRestSteps;
import io.github.awiodev.jbdd.examples.restassured.steps.UserApiSteps;
import io.github.awiodev.jbdd.junit5.JBddExtension;
import io.github.awiodev.jbdd.restassured.impl.JBddRestAssuredStandardSteps;
import io.github.awiodev.jbdd.restassured.impl.RestAssuredActions;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

public class UserApiRestTest {

    private static final int USER_SERVICE_PORT = 8093;

    private static final String USER_SERVICE_BASE_URL =
        String.format("http://localhost:%s", USER_SERVICE_PORT);

    private final WireMockServer server = new WireMockServer(USER_SERVICE_PORT);

    private final Faker faker = new Faker(new Random(24));

    private final FakeUserServiceSteps fakeUserServiceSteps =
        new FakeUserServiceSteps(server, faker);

    private final UserApiSteps userApiSteps = UserApiRestSteps.builder()
        .withBaseUrl(USER_SERVICE_BASE_URL)
        .withRestAssuredSteps(JBddRestAssuredStandardSteps.builder()
            .withRestAssuredActions(RestAssuredActions.builder()
                .withFilters(List.of(new RequestLoggingFilter(), new ResponseLoggingFilter()))
                .build())
            .build())
        .build();

    @RegisterExtension
    public JBddExtension extension = JBddExtension.builder()
        .withSetupAndTearDown(() -> JBddUserApiRun.builder()
                .withJBddSteps(JBddStandardSteps.builder().build())
                .withJBddStandardContextFactory(JBddStandardContextFactory.builder().build())
                .withFakeUserServiceSteps(fakeUserServiceSteps)
                .withApiSteps(userApiSteps)
                .withFaker(faker)
                .build(),
            JBddRun::clean)
        .build();

    @Nested
    class UserServiceCreatesNewUser {

        @Test
        void whenValidUserDataIsProvided(JBddUserApiRun jBddRun) {
            jBddRun.scenario().given("I have a request to create a valid user", () -> {
                var user = new User();
                user.setName(jBddRun.faker().name().fullName());
                user.setEmail(jBddRun.faker().internet().emailAddress());
                jBddRun.fakeUserServiceSteps().givenUserServiceAcceptsUser(user);
                jBddRun.context().store(user);
            }).when("I submit user creation", () -> {
                Response response =
                    jBddRun.userApiSteps().whenCreatesUser(jBddRun.context().get(User.class));
                jBddRun.context().store("response", response);
            }).then("User is successfully created in the system", () -> {
                Response response = jBddRun.context().get("response", Response.class);
                jBddRun.userApiSteps().thenUserIsCreatedSuccessfully(response);
            });
        }
    }

    @Nested
    class UserServiceReturnsUserData {

        @Test
        void whenValidUserIdIsProvided(JBddUserApiRun jBddRun) {
            jBddRun.scenario().given("I have valid user id", () -> {
                String userId = UUID.randomUUID().toString();
                jBddRun.context().store("userid", userId);
                jBddRun.fakeUserServiceSteps().givenUserServiceReturnsUser(userId);
            }).when("I try to retrieve user by id", () -> {
                Response response = jBddRun.userApiSteps()
                    .whenRetrievesUser(jBddRun.context().get("userid", String.class));
                jBddRun.context().store("response", response);
            }).then("Service returns user data", () -> {
                jBddRun.userApiSteps()
                    .thenUserDataIsRetrieved(jBddRun.context().get("response", Response.class));
            });
        }
    }
}
