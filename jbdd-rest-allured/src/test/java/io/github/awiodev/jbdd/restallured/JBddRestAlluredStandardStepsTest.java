package io.github.awiodev.jbdd.restallured;

import io.github.awiodev.jbdd.restassured.definition.AuthSupplier;
import io.github.awiodev.jbdd.restassured.definition.JBddRestAssuredSteps;
import io.restassured.response.Response;
import java.util.Map;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class JBddRestAlluredStandardStepsTest {

    private static final String BASE_URL = "https://baseurl.com";
    private static final String PATH = "/myPath";

    @Nested
    class WhenPostTo {

        @Test
        void usingBaseEndpointAndPath() {
            JBddRestAssuredSteps restAssuredSteps = Mockito.mock(JBddRestAssuredSteps.class);
            JBddRestAlluredStandardSteps steps = JBddRestAlluredStandardSteps.builder()
                .withRestAssuredSteps(restAssuredSteps).build();

            steps.whenPostTo(BASE_URL, PATH);

            Mockito.verify(restAssuredSteps, Mockito.times(1))
                .whenPostTo(BASE_URL, PATH);
        }

        @Test
        void usingBaseEndpointPathAndPayload() {
            JBddRestAssuredSteps restAssuredSteps = Mockito.mock(JBddRestAssuredSteps.class);
            JBddRestAlluredStandardSteps steps = JBddRestAlluredStandardSteps.builder()
                .withRestAssuredSteps(restAssuredSteps).build();

            Object value = new Object();

            steps.whenPostTo(BASE_URL, PATH, value);

            Mockito.verify(restAssuredSteps, Mockito.times(1))
                .whenPostTo(BASE_URL, PATH, value);
        }

        @Test
        void usingBaseEndpointPathAndAuthSupplier() {
            JBddRestAssuredSteps restAssuredSteps = Mockito.mock(JBddRestAssuredSteps.class);
            JBddRestAlluredStandardSteps steps = JBddRestAlluredStandardSteps.builder()
                .withRestAssuredSteps(restAssuredSteps).build();

            AuthSupplier authSupplier = requestSpecBuilder -> {
            };

            steps.whenPostTo(BASE_URL, PATH, authSupplier);

            Mockito.verify(restAssuredSteps, Mockito.times(1))
                .whenPostTo(BASE_URL, PATH, authSupplier);
        }

        @Test
        void usingBaseEndpointPathPayloadAndAuthProvider() {
            JBddRestAssuredSteps restAssuredSteps = Mockito.mock(JBddRestAssuredSteps.class);
            JBddRestAlluredStandardSteps steps = JBddRestAlluredStandardSteps.builder()
                .withRestAssuredSteps(restAssuredSteps).build();

            Object value = new Object();
            AuthSupplier authSupplier = requestSpecBuilder -> {
            };

            steps.whenPostTo(BASE_URL, PATH, value, authSupplier);

            Mockito.verify(restAssuredSteps, Mockito.times(1))
                .whenPostTo(BASE_URL, PATH, value, authSupplier);
        }
    }

    @Nested
    class WhenPutTo {

        @Test
        void usingBaseEndpointAndPath() {
            JBddRestAssuredSteps restAssuredSteps = Mockito.mock(JBddRestAssuredSteps.class);
            JBddRestAlluredStandardSteps steps = JBddRestAlluredStandardSteps.builder()
                .withRestAssuredSteps(restAssuredSteps).build();

            steps.whenPutTo(BASE_URL, PATH);

            Mockito.verify(restAssuredSteps, Mockito.times(1))
                .whenPutTo(BASE_URL, PATH);
        }

        @Test
        void usingBaseEndpointPathAndPayload() {
            JBddRestAssuredSteps restAssuredSteps = Mockito.mock(JBddRestAssuredSteps.class);
            JBddRestAlluredStandardSteps steps = JBddRestAlluredStandardSteps.builder()
                .withRestAssuredSteps(restAssuredSteps).build();

            Object value = new Object();
            steps.whenPutTo(BASE_URL, PATH, value);

            Mockito.verify(restAssuredSteps, Mockito.times(1))
                .whenPutTo(BASE_URL, PATH, value);
        }

        @Test
        void usingBaseEndpointPathAndAuthSupplier() {
            JBddRestAssuredSteps restAssuredSteps = Mockito.mock(JBddRestAssuredSteps.class);
            JBddRestAlluredStandardSteps steps = JBddRestAlluredStandardSteps.builder()
                .withRestAssuredSteps(restAssuredSteps).build();

            AuthSupplier authSupplier = requestSpecBuilder -> {
            };

            steps.whenPutTo(BASE_URL, PATH, authSupplier);

            Mockito.verify(restAssuredSteps, Mockito.times(1))
                .whenPutTo(BASE_URL, PATH, authSupplier);
        }

        @Test
        void usingBaseEndpointPathPayloadAndAuthProvider() {
            JBddRestAssuredSteps restAssuredSteps = Mockito.mock(JBddRestAssuredSteps.class);
            JBddRestAlluredStandardSteps steps = JBddRestAlluredStandardSteps.builder()
                .withRestAssuredSteps(restAssuredSteps).build();


            Object value = new Object();
            AuthSupplier authSupplier = requestSpecBuilder -> {
            };

            steps.whenPutTo(BASE_URL, PATH, value, authSupplier);

            Mockito.verify(restAssuredSteps, Mockito.times(1))
                .whenPutTo(BASE_URL, PATH, value, authSupplier);
        }
    }

    @Nested
    class WhenPatch {

        @Test
        void usingBaseEndpointPathAndPayload() {
            JBddRestAssuredSteps restAssuredSteps = Mockito.mock(JBddRestAssuredSteps.class);
            JBddRestAlluredStandardSteps steps = JBddRestAlluredStandardSteps.builder()
                .withRestAssuredSteps(restAssuredSteps).build();

            Object value = new Object();

            steps.whenPatch(BASE_URL, PATH, value);

            Mockito.verify(restAssuredSteps, Mockito.times(1))
                .whenPatch(BASE_URL, PATH, value);
        }

        @Test
        void usingBaseEndpointPathPayloadAndAuthProvider() {
            JBddRestAssuredSteps restAssuredSteps = Mockito.mock(JBddRestAssuredSteps.class);
            JBddRestAlluredStandardSteps steps = JBddRestAlluredStandardSteps.builder()
                .withRestAssuredSteps(restAssuredSteps).build();

            Object value = new Object();
            AuthSupplier authSupplier = requestSpecBuilder -> {
            };

            steps.whenPatch(BASE_URL, PATH, value, authSupplier);

            Mockito.verify(restAssuredSteps, Mockito.times(1))
                .whenPatch(BASE_URL, PATH, value, authSupplier);
        }
    }

    @Nested
    class WhenGetFrom {

        @Test
        void usingBaseEndpointAndPath() {
            JBddRestAssuredSteps restAssuredSteps = Mockito.mock(JBddRestAssuredSteps.class);
            JBddRestAlluredStandardSteps steps = JBddRestAlluredStandardSteps.builder()
                .withRestAssuredSteps(restAssuredSteps).build();

            steps.whenGetFrom(BASE_URL, PATH);

            Mockito.verify(restAssuredSteps, Mockito.times(1))
                .whenGetFrom(BASE_URL, PATH);
        }

        @Test
        void usingBaseEndpointPathAndAuthSupplier() {
            JBddRestAssuredSteps restAssuredSteps = Mockito.mock(JBddRestAssuredSteps.class);
            JBddRestAlluredStandardSteps steps = JBddRestAlluredStandardSteps.builder()
                .withRestAssuredSteps(restAssuredSteps).build();

            AuthSupplier authSupplier = requestSpecBuilder -> {
            };

            steps.whenGetFrom(BASE_URL, PATH, authSupplier);

            Mockito.verify(restAssuredSteps, Mockito.times(1))
                .whenGetFrom(BASE_URL, PATH, authSupplier);
        }

        @Test
        void usingBaseEndpointPathAndQuery() {
            JBddRestAssuredSteps restAssuredSteps = Mockito.mock(JBddRestAssuredSteps.class);
            JBddRestAlluredStandardSteps steps = JBddRestAlluredStandardSteps.builder()
                .withRestAssuredSteps(restAssuredSteps).build();

            Map<String, Object> query = Map.of("1", "2");
            steps.whenGetFrom(BASE_URL, PATH, query);

            Mockito.verify(restAssuredSteps, Mockito.times(1))
                .whenGetFrom(BASE_URL, PATH, query);
        }

        @Test
        void usingBaseEndpointPathQueryAndAuthSupplier() {
            JBddRestAssuredSteps restAssuredSteps = Mockito.mock(JBddRestAssuredSteps.class);
            JBddRestAlluredStandardSteps steps = JBddRestAlluredStandardSteps.builder()
                .withRestAssuredSteps(restAssuredSteps).build();

            Map<String, Object> query = Map.of("1", "2");
            AuthSupplier authSupplier = requestSpecBuilder -> {
            };

            steps.whenGetFrom(BASE_URL, PATH, query, authSupplier);

            Mockito.verify(restAssuredSteps, Mockito.times(1))
                .whenGetFrom(BASE_URL, PATH, query, authSupplier);
        }
    }

    @Nested
    class WhenDelete {

        @Test
        void usingBaseEndpointAndPath() {
            JBddRestAssuredSteps restAssuredSteps = Mockito.mock(JBddRestAssuredSteps.class);
            JBddRestAlluredStandardSteps steps = JBddRestAlluredStandardSteps.builder()
                .withRestAssuredSteps(restAssuredSteps).build();

            steps.whenDelete(BASE_URL, PATH);

            Mockito.verify(restAssuredSteps, Mockito.times(1))
                .whenDelete(BASE_URL, PATH);
        }

        @Test
        void usingBaseEndpointPathAndAuthSupplier() {
            JBddRestAssuredSteps restAssuredSteps = Mockito.mock(JBddRestAssuredSteps.class);
            JBddRestAlluredStandardSteps steps = JBddRestAlluredStandardSteps.builder()
                .withRestAssuredSteps(restAssuredSteps).build();

            AuthSupplier authSupplier = requestSpecBuilder -> {
            };

            steps.whenDelete(BASE_URL, PATH, authSupplier);

            Mockito.verify(restAssuredSteps, Mockito.times(1))
                .whenDelete(BASE_URL, PATH, authSupplier);
        }
    }

    @Nested
    class ThenResponseStatusIs {

        @Test
        void invokesSameMethodOnRestAssuredSteps() {
            JBddRestAssuredSteps restAssuredSteps = Mockito.mock(JBddRestAssuredSteps.class);
            JBddRestAlluredStandardSteps steps = JBddRestAlluredStandardSteps.builder()
                .withRestAssuredSteps(restAssuredSteps).build();

            var response = Mockito.mock(Response.class);
            var status = 201;

            steps.thenResponseStatusIs(response, status);

            Mockito.verify(restAssuredSteps, Mockito.times(1))
                .thenResponseStatusIs(response, status);
        }
    }

    @Nested
    class ThenResponseDeserializesTo {

        @Test
        void invokesSameMethodOnRestAssuredSteps() {
            JBddRestAssuredSteps restAssuredSteps = Mockito.mock(JBddRestAssuredSteps.class);
            JBddRestAlluredStandardSteps steps = JBddRestAlluredStandardSteps.builder()
                .withRestAssuredSteps(restAssuredSteps).build();

            var response = Mockito.mock(Response.class);
            var clazz = Object.class;

            steps.thenResponseDeserializesTo(response, clazz);

            Mockito.verify(restAssuredSteps, Mockito.times(1))
                .thenResponseDeserializesTo(response, clazz);
        }
    }

    @Nested
    class Builder {

        @Test
        void createsDefaultStepsWhenNotProvided() {
            JBddRestAlluredStandardSteps.builder().build();
        }
    }
}
