package io.github.awiodev.jbdd.restassured.impl;

import io.github.awiodev.jbdd.restassured.definition.AuthSupplier;
import io.github.awiodev.jbdd.restassured.definition.JBddRestAssuredSteps;
import io.restassured.response.Response;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class JBddRestAssuredStandardStepsTest {

    private static final String BASE_URL = "https://baseurl.com";
    private static final String PATH = "/myPath";

    @Nested
    class WhenPostTo {

        @Test
        void usingBaseEndpointAndPath() {
            RestAssuredActions actions = Mockito.mock(RestAssuredActions.class);
            JBddRestAssuredSteps steps = JBddRestAssuredStandardSteps.builder()
                .withRestAssuredActions(actions).build();

            steps.whenPostTo(BASE_URL, PATH);

            Mockito.verify(actions, Mockito.times(1))
                .post(BASE_URL, PATH);
        }

        @Test
        void usingBaseEndpointPathAndPayload() {

            RestAssuredActions actions = Mockito.mock(RestAssuredActions.class);
            JBddRestAssuredSteps steps = JBddRestAssuredStandardSteps.builder()
                .withRestAssuredActions(actions).build();

            Object value = new Object();
            steps.whenPostTo(BASE_URL, PATH, value);

            Mockito.verify(actions, Mockito.times(1))
                .postJson(BASE_URL, PATH, value);
        }

        @Test
        void usingBaseEndpointPathAndAuthSupplier() {
            RestAssuredActions actions = Mockito.mock(RestAssuredActions.class);
            JBddRestAssuredSteps steps = JBddRestAssuredStandardSteps.builder()
                .withRestAssuredActions(actions).build();

            AuthSupplier authSupplier = requestSpecBuilder -> {
            };
            steps.whenPostTo(BASE_URL, PATH, authSupplier);

            Mockito.verify(actions, Mockito.times(1))
                .post(BASE_URL, PATH, authSupplier);
        }

        @Test
        void usingBaseEndpointPathPayloadAndAuthProvider() {

            RestAssuredActions actions = Mockito.mock(RestAssuredActions.class);
            JBddRestAssuredSteps steps = JBddRestAssuredStandardSteps.builder()
                .withRestAssuredActions(actions).build();

            Object value = new Object();
            AuthSupplier authSupplier = requestSpecBuilder -> {
            };
            steps.whenPostTo(BASE_URL, PATH, value, authSupplier);

            Mockito.verify(actions, Mockito.times(1))
                .postJson(BASE_URL, PATH, value, authSupplier);
        }
    }

    @Nested
    class WhenPutTo {

        @Test
        void usingBaseEndpointAndPath() {
            RestAssuredActions actions = Mockito.mock(RestAssuredActions.class);
            JBddRestAssuredSteps steps = JBddRestAssuredStandardSteps.builder()
                .withRestAssuredActions(actions).build();

            steps.whenPutTo(BASE_URL, PATH);

            Mockito.verify(actions, Mockito.times(1))
                .put(BASE_URL, PATH);
        }

        @Test
        void usingBaseEndpointPathAndPayload() {

            RestAssuredActions actions = Mockito.mock(RestAssuredActions.class);
            JBddRestAssuredSteps steps = JBddRestAssuredStandardSteps.builder()
                .withRestAssuredActions(actions).build();

            Object value = new Object();
            steps.whenPutTo(BASE_URL, PATH, value);

            Mockito.verify(actions, Mockito.times(1))
                .putJson(BASE_URL, PATH, value);
        }

        @Test
        void usingBaseEndpointPathAndAuthSupplier() {
            RestAssuredActions actions = Mockito.mock(RestAssuredActions.class);
            JBddRestAssuredSteps steps = JBddRestAssuredStandardSteps.builder()
                .withRestAssuredActions(actions).build();

            AuthSupplier authSupplier = requestSpecBuilder -> {
            };
            steps.whenPutTo(BASE_URL, PATH, authSupplier);

            Mockito.verify(actions, Mockito.times(1))
                .put(BASE_URL, PATH, authSupplier);
        }

        @Test
        void usingBaseEndpointPathPayloadAndAuthProvider() {

            RestAssuredActions actions = Mockito.mock(RestAssuredActions.class);
            JBddRestAssuredSteps steps = JBddRestAssuredStandardSteps.builder()
                .withRestAssuredActions(actions).build();

            Object value = new Object();
            AuthSupplier authSupplier = requestSpecBuilder -> {
            };
            steps.whenPutTo(BASE_URL, PATH, value, authSupplier);

            Mockito.verify(actions, Mockito.times(1))
                .putJson(BASE_URL, PATH, value, authSupplier);
        }
    }

    @Nested
    class WhenPatch {

        @Test
        void usingBaseEndpointPathAndPayload() {

            RestAssuredActions actions = Mockito.mock(RestAssuredActions.class);
            JBddRestAssuredSteps steps = JBddRestAssuredStandardSteps.builder()
                .withRestAssuredActions(actions).build();

            Object value = new Object();
            steps.whenPatch(BASE_URL, PATH, value);

            Mockito.verify(actions, Mockito.times(1))
                .patch(BASE_URL, PATH, value);
        }

        @Test
        void usingBaseEndpointPathPayloadAndAuthProvider() {

            RestAssuredActions actions = Mockito.mock(RestAssuredActions.class);
            JBddRestAssuredSteps steps = JBddRestAssuredStandardSteps.builder()
                .withRestAssuredActions(actions).build();

            Object value = new Object();
            AuthSupplier authSupplier = requestSpecBuilder -> {
            };
            steps.whenPatch(BASE_URL, PATH, value, authSupplier);

            Mockito.verify(actions, Mockito.times(1))
                .patch(BASE_URL, PATH, value, authSupplier);
        }
    }

    @Nested
    class WhenGetFrom {

        @Test
        void usingBaseEndpointAndPath() {
            RestAssuredActions actions = Mockito.mock(RestAssuredActions.class);
            JBddRestAssuredSteps steps = JBddRestAssuredStandardSteps.builder()
                .withRestAssuredActions(actions).build();

            steps.whenGetFrom(BASE_URL, PATH);

            Mockito.verify(actions, Mockito.times(1))
                .get(BASE_URL, PATH);
        }

        @Test
        void usingBaseEndpointPathAndAuthSupplier() {
            RestAssuredActions actions = Mockito.mock(RestAssuredActions.class);
            JBddRestAssuredSteps steps = JBddRestAssuredStandardSteps.builder()
                .withRestAssuredActions(actions).build();

            AuthSupplier authSupplier = requestSpecBuilder -> {
            };
            steps.whenGetFrom(BASE_URL, PATH, authSupplier);

            Mockito.verify(actions, Mockito.times(1))
                .get(BASE_URL, PATH, authSupplier);
        }

        @Test
        void usingBaseEndpointPathAndQuery() {

            RestAssuredActions actions = Mockito.mock(RestAssuredActions.class);
            JBddRestAssuredSteps steps = JBddRestAssuredStandardSteps.builder()
                .withRestAssuredActions(actions).build();

            Map<String, Object> query = Map.of("1", "2");
            steps.whenGetFrom(BASE_URL, PATH, query);

            Mockito.verify(actions, Mockito.times(1))
                .get(BASE_URL, PATH, query);

        }

        @Test
        void usingBaseEndpointPathQueryAndAuthSupplier() {
            RestAssuredActions actions = Mockito.mock(RestAssuredActions.class);
            JBddRestAssuredSteps steps = JBddRestAssuredStandardSteps.builder()
                .withRestAssuredActions(actions).build();

            Map<String, Object> query = Map.of("1", "2");
            AuthSupplier authSupplier = requestSpecBuilder -> {
            };
            steps.whenGetFrom(BASE_URL, PATH, query, authSupplier);

            Mockito.verify(actions, Mockito.times(1))
                .get(BASE_URL, PATH, query, authSupplier);
        }
    }

    @Nested
    class WhenDelete {

        @Test
        void usingBaseEndpointAndPath() {
            RestAssuredActions actions = Mockito.mock(RestAssuredActions.class);
            JBddRestAssuredSteps steps = JBddRestAssuredStandardSteps.builder()
                .withRestAssuredActions(actions).build();

            steps.whenDelete(BASE_URL, PATH);

            Mockito.verify(actions, Mockito.times(1))
                .delete(BASE_URL, PATH);
        }

        @Test
        void usingBaseEndpointPathAndAuthSupplier() {
            RestAssuredActions actions = Mockito.mock(RestAssuredActions.class);
            JBddRestAssuredSteps steps = JBddRestAssuredStandardSteps.builder()
                .withRestAssuredActions(actions).build();

            AuthSupplier authSupplier = requestSpecBuilder -> {
            };
            steps.whenDelete(BASE_URL, PATH, authSupplier);

            Mockito.verify(actions, Mockito.times(1))
                .delete(BASE_URL, PATH, authSupplier);
        }
    }

    @Nested
    class ThenResponseStatusIs {

        @Test
        void verifiesStatusCodeOfTheResponse() {

            RestAssuredActions actions = Mockito.mock(RestAssuredActions.class);
            JBddRestAssuredSteps steps = JBddRestAssuredStandardSteps.builder()
                .withRestAssuredActions(actions).build();

            var response = Mockito.mock(Response.class);
            Mockito.when(response.statusCode()).thenReturn(201);
            var status = 201;

            steps.thenResponseStatusIs(response, status);
        }
    }

    @Nested
    class ThenResponseDeserializesTo {

        @Test
        void deserializesResponseToGivenType() {
            RestAssuredActions actions = Mockito.mock(RestAssuredActions.class);
            JBddRestAssuredSteps steps = JBddRestAssuredStandardSteps.builder()
                .withRestAssuredActions(actions).build();

            String expected = "Hello world";
            var response = Mockito.mock(Response.class);
            Mockito.when(response.as(String.class)).thenReturn(expected);
            var status = 201;

            String actual = steps.thenResponseDeserializesTo(response, String.class);
            Assertions.assertThat(actual).isEqualTo(expected);
        }
    }

    @Nested
    class Builder {

        @Test
        void createsDefaultActionsWhenNotProvided() {
            JBddRestAssuredStandardSteps.builder().build();
        }
    }
}
