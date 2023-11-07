package io.github.awiodev.jbdd.examples.restassured.steps;

import io.github.awiodev.jbdd.examples.restassured.model.User;
import io.github.awiodev.jbdd.restassured.definition.JBddRestAssuredSteps;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;

public class UserApiRestSteps implements UserApiSteps {

    private static final String POST_PATH = "/user";
    private static final String GET_PATH = "/user/{id}";
    private final String baseUrl;
    private final JBddRestAssuredSteps restAssuredSteps;

    private UserApiRestSteps(String baseUrl, JBddRestAssuredSteps restAssuredSteps) {
        this.baseUrl = baseUrl;
        this.restAssuredSteps = restAssuredSteps;
    }

    @Override
    public Response whenCreatesUser(User payload) {
        return restAssuredSteps.whenPostTo(baseUrl, POST_PATH, payload);
    }

    @Override
    public Response whenRetrievesUser(String userId) {
        String path = GET_PATH.replace("{id}", userId);
        return restAssuredSteps.whenGetFrom(baseUrl, path);
    }

    @Override
    public User thenUserIsCreatedSuccessfully(Response response) {
        Assertions.assertThat(response.statusCode()).isEqualTo(201);
        User deserialized = restAssuredSteps.thenResponseDeserializesTo(response, User.class);
        Assertions.assertThat(deserialized.getId()).isNotBlank();
        return deserialized;
    }

    @Override
    public User thenUserDataIsRetrieved(Response response) {
        Assertions.assertThat(response.statusCode()).isEqualTo(200);
        return restAssuredSteps.thenResponseDeserializesTo(response, User.class);
    }

    public static UserApiRestStepsBuilder builder() {
        return new UserApiRestStepsBuilder();
    }

    public static final class UserApiRestStepsBuilder {
        private String baseUrl;
        private JBddRestAssuredSteps restAssuredSteps;

        private UserApiRestStepsBuilder() {
        }

        public UserApiRestStepsBuilder withBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public UserApiRestStepsBuilder withRestAssuredSteps(JBddRestAssuredSteps restAssuredSteps) {
            this.restAssuredSteps = restAssuredSteps;
            return this;
        }

        public UserApiRestSteps build() {
            return new UserApiRestSteps(baseUrl, restAssuredSteps);
        }
    }
}
