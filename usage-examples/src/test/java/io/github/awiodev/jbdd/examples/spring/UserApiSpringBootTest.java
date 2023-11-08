package io.github.awiodev.jbdd.examples.spring;

import io.github.awiodev.jbdd.examples.restassured.custom.JBddUserApiRun;
import io.github.awiodev.jbdd.examples.restassured.model.User;
import io.github.awiodev.jbdd.examples.springboot.JBddRestAlluredConfiguration;
import io.github.awiodev.jbdd.junit5.JBddExtension;
import io.restassured.response.Response;
import java.util.UUID;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = {JBddRestAlluredConfiguration.class})
public class UserApiSpringBootTest {

    @RegisterExtension
    @Autowired
    public JBddExtension jBddExtension;

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
