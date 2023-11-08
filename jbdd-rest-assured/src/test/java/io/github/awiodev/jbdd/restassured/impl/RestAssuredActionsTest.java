package io.github.awiodev.jbdd.restassured.impl;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.http.RequestMethod;
import com.github.tomakehurst.wiremock.stubbing.ServeEvent;
import com.github.tomakehurst.wiremock.verification.LoggedRequest;
import io.github.awiodev.jbdd.restassured.definition.AuthSupplier;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.Filter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RestAssuredActionsTest {

    private static final int PORT = 8085;
    private static final String URL = String.format("http://localhost:%s", PORT);
    private final WireMockServer wireMockServer = new WireMockServer(PORT);

    @BeforeAll
    void beforeAll() {
        wireMockServer.start();
    }

    @AfterAll
    void afterAll() {
        wireMockServer.stop();
    }

    @AfterEach
    void afterEach() {
        wireMockServer.resetAll();
    }

    private static class PayloadObject {

        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @Nested
    class Builder {

        @Test
        void allowsToNotProvideAnyBuilderObject() {
            RestAssuredActions.builder().build();
        }

        @Test
        void allowsToProvideBuilderObjects() {
            RestAssuredActions.builder()
                .withAuthSupplier(
                    requestSpecBuilder -> requestSpecBuilder
                        .addHeader("Authorization", "token"))
                .withRestAssuredConfig(RestAssuredConfig.config())
                .withFilters(new ArrayList<>())
                .build();
        }
    }

    @Nested
    class CreateDefaultSpecBuilder {

        @Test
        void preparesRequestSpecificationBuilder() {
            var actions = RestAssuredActions.builder().build();
            RequestSpecBuilder spec =
                actions.createDefaultSpecBuilder(URL, "/mypath", new NoAuthSupplier());
            Assertions.assertThat(spec).isNotNull();
        }
    }

    @Nested
    class CreateJsonPayloadSpecBuilder {

        @Test
        void preparesRequestSpecificationBuilderForJsonPayload() {
            var payload = new PayloadObject();
            var actions = RestAssuredActions.builder().build();
            RequestSpecBuilder spec =
                actions.createJsonPayloadSpecBuilder(URL, "/mypath", payload,
                    new NoAuthSupplier());
            Assertions.assertThat(spec).isNotNull();
        }
    }

    @Nested
    class PostJson {

        @Test
        void usingBaseUrlPathPayloadAndAuthSupplier() {
            String path = "/create";
            var payload = new PayloadObject();
            payload.setId("4a5d60f5-92f1-4f36-81f7-494c102e5032");
            payload.setName("my name");

            wireMockServer.stubFor(WireMock.post(path)
                .willReturn(WireMock.aResponse()
                    .withStatus(201)
                )
            );

            var actions = RestAssuredActions.builder()
                .build();

            actions.postJson(URL, path, payload, requestSpecBuilder -> requestSpecBuilder
                .addHeader("Authorization", "secret"));

            List<ServeEvent> events = wireMockServer.getAllServeEvents();

            Assertions.assertThat(events.size()).isEqualTo(1);

            LoggedRequest loggedRequest = events.get(0).getRequest();
            Assertions.assertThat(loggedRequest.getUrl()).isEqualTo(path);
            Assertions.assertThat(loggedRequest.getMethod()).isEqualTo(RequestMethod.POST);
            String actualBody = new String(loggedRequest.getBody());
            Assertions.assertThat(actualBody).isEqualTo(
                "{\"id\":\"4a5d60f5-92f1-4f36-81f7-494c102e5032\",\"name\":\"my name\"}");
            Assertions.assertThat(loggedRequest.getHeader("Authorization"))
                .isEqualTo("secret");
        }

        @Test
        void usingBaseUrlPathAndPayload() {
            String path = "/create";
            var payload = new PayloadObject();
            payload.setId("4a5d60f5-92f1-4f36-81f7-494c102e5010");
            payload.setName("my name");

            wireMockServer.stubFor(WireMock.post(path)
                .willReturn(WireMock.aResponse()
                    .withStatus(201)
                )
            );

            var actions = RestAssuredActions.builder()
                .withAuthSupplier(requestSpecBuilder -> requestSpecBuilder
                    .addHeader("Authorization", "secret"))
                .build();

            actions.postJson(URL, path, payload);

            List<ServeEvent> events = wireMockServer.getAllServeEvents();

            Assertions.assertThat(events.size()).isEqualTo(1);

            LoggedRequest loggedRequest = events.get(0).getRequest();
            Assertions.assertThat(loggedRequest.getUrl()).isEqualTo(path);
            Assertions.assertThat(loggedRequest.getMethod()).isEqualTo(RequestMethod.POST);
            String actualBody = new String(loggedRequest.getBody());
            Assertions.assertThat(actualBody).isEqualTo(
                "{\"id\":\"4a5d60f5-92f1-4f36-81f7-494c102e5010\",\"name\":\"my name\"}");
            Assertions.assertThat(loggedRequest.getHeader("Authorization"))
                .isEqualTo("secret");
        }
    }

    @Nested
    class Post {

        @Test
        void usingBaseUrlPathAndAuthSupplier() {
            String path = "/create-no-payload";

            wireMockServer.stubFor(WireMock.post(path)
                .willReturn(WireMock.aResponse()
                    .withStatus(201)
                )
            );

            var actions = RestAssuredActions.builder()
                .build();

            actions.post(URL, path, requestSpecBuilder -> requestSpecBuilder
                .addHeader("Authorization", "secret"));

            List<ServeEvent> events = wireMockServer.getAllServeEvents();

            Assertions.assertThat(events.size()).isEqualTo(1);

            LoggedRequest loggedRequest = events.get(0).getRequest();
            Assertions.assertThat(loggedRequest.getUrl()).isEqualTo(path);
            Assertions.assertThat(loggedRequest.getMethod()).isEqualTo(RequestMethod.POST);
            Assertions.assertThat(loggedRequest.getHeader("Authorization"))
                .isEqualTo("secret");
        }

        @Test
        void usingBaseUrlAndPath() {
            String path = "/create-no-payload";

            wireMockServer.stubFor(WireMock.post(path)
                .willReturn(WireMock.aResponse()
                    .withStatus(201)
                )
            );

            var actions = RestAssuredActions.builder()
                .withAuthSupplier(requestSpecBuilder -> requestSpecBuilder
                    .addHeader("Authorization", "secret"))
                .build();

            actions.post(URL, path);

            List<ServeEvent> events = wireMockServer.getAllServeEvents();

            Assertions.assertThat(events.size()).isEqualTo(1);

            LoggedRequest loggedRequest = events.get(0).getRequest();
            Assertions.assertThat(loggedRequest.getUrl()).isEqualTo(path);
            Assertions.assertThat(loggedRequest.getMethod()).isEqualTo(RequestMethod.POST);
            Assertions.assertThat(loggedRequest.getHeader("Authorization"))
                .isEqualTo("secret");
        }
    }

    @Nested
    class PutJson {

        @Test
        void usingBaseUrlPathPayloadAndAuthSupplier() {
            String path = "/update";
            var payload = new PayloadObject();
            payload.setId("4a5d60f5-92f1-4f36-81f7-494c102e5032");
            payload.setName("my name");

            wireMockServer.stubFor(WireMock.post(path)
                .willReturn(WireMock.aResponse()
                    .withStatus(201)
                )
            );

            var actions = RestAssuredActions.builder()
                .build();

            actions.putJson(URL, path, payload, requestSpecBuilder -> requestSpecBuilder
                .addHeader("Authorization", "secret"));

            List<ServeEvent> events = wireMockServer.getAllServeEvents();

            Assertions.assertThat(events.size()).isEqualTo(1);

            LoggedRequest loggedRequest = events.get(0).getRequest();
            Assertions.assertThat(loggedRequest.getUrl()).isEqualTo(path);
            Assertions.assertThat(loggedRequest.getMethod()).isEqualTo(RequestMethod.PUT);
            String actualBody = new String(loggedRequest.getBody());
            Assertions.assertThat(actualBody).isEqualTo(
                "{\"id\":\"4a5d60f5-92f1-4f36-81f7-494c102e5032\",\"name\":\"my name\"}");
            Assertions.assertThat(loggedRequest.getHeader("Authorization"))
                .isEqualTo("secret");
        }

        @Test
        void usingBaseUrlPathAndPayload() {
            String path = "/update";
            var payload = new PayloadObject();
            payload.setId("4a5d60f5-92f1-4f36-81f7-494c102e5010");
            payload.setName("my name");

            wireMockServer.stubFor(WireMock.post(path)
                .willReturn(WireMock.aResponse()
                    .withStatus(201)
                )
            );

            var actions = RestAssuredActions.builder()
                .withAuthSupplier(requestSpecBuilder -> requestSpecBuilder
                    .addHeader("Authorization", "secret"))
                .build();

            actions.putJson(URL, path, payload);

            List<ServeEvent> events = wireMockServer.getAllServeEvents();

            Assertions.assertThat(events.size()).isEqualTo(1);

            LoggedRequest loggedRequest = events.get(0).getRequest();
            Assertions.assertThat(loggedRequest.getUrl()).isEqualTo(path);
            Assertions.assertThat(loggedRequest.getMethod()).isEqualTo(RequestMethod.PUT);
            String actualBody = new String(loggedRequest.getBody());
            Assertions.assertThat(actualBody).isEqualTo(
                "{\"id\":\"4a5d60f5-92f1-4f36-81f7-494c102e5010\",\"name\":\"my name\"}");
            Assertions.assertThat(loggedRequest.getHeader("Authorization"))
                .isEqualTo("secret");
        }
    }

    @Nested
    class Put {

        @Test
        void usingBaseUrlPathAndAuthSupplier() {
            String path = "/update-no-payload";

            wireMockServer.stubFor(WireMock.put(path)
                .willReturn(WireMock.aResponse()
                    .withStatus(201)
                )
            );

            var actions = RestAssuredActions.builder()
                .build();

            actions.put(URL, path, requestSpecBuilder -> requestSpecBuilder
                .addHeader("Authorization", "secret"));

            List<ServeEvent> events = wireMockServer.getAllServeEvents();

            Assertions.assertThat(events.size()).isEqualTo(1);

            LoggedRequest loggedRequest = events.get(0).getRequest();
            Assertions.assertThat(loggedRequest.getUrl()).isEqualTo(path);
            Assertions.assertThat(loggedRequest.getMethod()).isEqualTo(RequestMethod.PUT);
            Assertions.assertThat(loggedRequest.getHeader("Authorization"))
                .isEqualTo("secret");
        }

        @Test
        void usingBaseUrlAndPath() {
            String path = "/update-no-payload";

            wireMockServer.stubFor(WireMock.put(path)
                .willReturn(WireMock.aResponse()
                    .withStatus(201)
                )
            );

            var actions = RestAssuredActions.builder()
                .withAuthSupplier(requestSpecBuilder -> requestSpecBuilder
                    .addHeader("Authorization", "secret"))
                .build();

            actions.put(URL, path);

            List<ServeEvent> events = wireMockServer.getAllServeEvents();

            Assertions.assertThat(events.size()).isEqualTo(1);

            LoggedRequest loggedRequest = events.get(0).getRequest();
            Assertions.assertThat(loggedRequest.getUrl()).isEqualTo(path);
            Assertions.assertThat(loggedRequest.getMethod()).isEqualTo(RequestMethod.PUT);
            Assertions.assertThat(loggedRequest.getHeader("Authorization"))
                .isEqualTo("secret");
        }
    }

    @Nested
    class Patch {

        @Test
        void usingBaseUrlPathPayloadAndAuthSupplier() {
            String path = "/patch";
            var payload = new PayloadObject();
            payload.setId("4a5d60f5-92f1-4f36-81f7-494c102e5032");
            payload.setName("my name");

            wireMockServer.stubFor(WireMock.patch(path)
                .willReturn(WireMock.aResponse()
                    .withStatus(201)
                )
            );

            var actions = RestAssuredActions.builder()
                .build();

            actions.patch(URL, path, payload, requestSpecBuilder -> requestSpecBuilder
                .addHeader("Authorization", "secret"));

            List<ServeEvent> events = wireMockServer.getAllServeEvents();

            Assertions.assertThat(events.size()).isEqualTo(1);

            LoggedRequest loggedRequest = events.get(0).getRequest();
            Assertions.assertThat(loggedRequest.getUrl()).isEqualTo(path);
            Assertions.assertThat(loggedRequest.getMethod()).isEqualTo(RequestMethod.PATCH);
            String actualBody = new String(loggedRequest.getBody());
            Assertions.assertThat(actualBody).isEqualTo(
                "{\"id\":\"4a5d60f5-92f1-4f36-81f7-494c102e5032\",\"name\":\"my name\"}");
            Assertions.assertThat(loggedRequest.getHeader("Authorization"))
                .isEqualTo("secret");
        }

        @Test
        void usingBaseUrlPathAndPayload() {
            String path = "/patch";
            var payload = new PayloadObject();
            payload.setId("4a5d60f5-92f1-4f36-81f7-494c102e5010");
            payload.setName("my name");

            wireMockServer.stubFor(WireMock.patch(path)
                .willReturn(WireMock.aResponse()
                    .withStatus(201)
                )
            );

            var actions = RestAssuredActions.builder()
                .withAuthSupplier(requestSpecBuilder -> requestSpecBuilder
                    .addHeader("Authorization", "secret"))
                .build();

            actions.patch(URL, path, payload);

            List<ServeEvent> events = wireMockServer.getAllServeEvents();

            Assertions.assertThat(events.size()).isEqualTo(1);

            LoggedRequest loggedRequest = events.get(0).getRequest();
            Assertions.assertThat(loggedRequest.getUrl()).isEqualTo(path);
            Assertions.assertThat(loggedRequest.getMethod()).isEqualTo(RequestMethod.PATCH);
            String actualBody = new String(loggedRequest.getBody());
            Assertions.assertThat(actualBody).isEqualTo(
                "{\"id\":\"4a5d60f5-92f1-4f36-81f7-494c102e5010\",\"name\":\"my name\"}");
            Assertions.assertThat(loggedRequest.getHeader("Authorization"))
                .isEqualTo("secret");
        }
    }

    @Nested
    class Get {

        @Test
        void usingBaseUrlPathAndAuthSupplier() {
            String path = "/get";

            wireMockServer.stubFor(WireMock.get(path)
                .willReturn(WireMock.aResponse()
                    .withStatus(200)
                )
            );

            var actions = RestAssuredActions.builder()
                .build();

            actions.get(URL, path, requestSpecBuilder -> requestSpecBuilder
                .addHeader("Authorization", "secret"));

            List<ServeEvent> events = wireMockServer.getAllServeEvents();

            Assertions.assertThat(events.size()).isEqualTo(1);

            LoggedRequest loggedRequest = events.get(0).getRequest();
            Assertions.assertThat(loggedRequest.getUrl()).isEqualTo(path);
            Assertions.assertThat(loggedRequest.getMethod()).isEqualTo(RequestMethod.GET);
            Assertions.assertThat(loggedRequest.getHeader("Authorization"))
                .isEqualTo("secret");
        }

        @Test
        void usingBaseUrlAndPath() {
            String path = "/get";

            wireMockServer.stubFor(WireMock.get(path)
                .willReturn(WireMock.aResponse()
                    .withStatus(200)
                )
            );

            var actions = RestAssuredActions.builder()
                .withAuthSupplier(requestSpecBuilder -> requestSpecBuilder
                    .addHeader("Authorization", "secret"))
                .build();

            actions.get(URL, path);

            List<ServeEvent> events = wireMockServer.getAllServeEvents();

            Assertions.assertThat(events.size()).isEqualTo(1);

            LoggedRequest loggedRequest = events.get(0).getRequest();
            Assertions.assertThat(loggedRequest.getUrl()).isEqualTo(path);
            Assertions.assertThat(loggedRequest.getMethod()).isEqualTo(RequestMethod.GET);
            Assertions.assertThat(loggedRequest.getHeader("Authorization"))
                .isEqualTo("secret");
        }

        @Test
        void usingBaseUrlPathQueryAndAuthSupplier() {
            String path = "/get";

            wireMockServer.stubFor(WireMock.get(path)
                .willReturn(WireMock.aResponse()
                    .withStatus(200)
                )
            );

            var actions = RestAssuredActions.builder()
                .build();

            Map<String, Object> query = Map.of("key", "value");

            actions.get(URL, path, query, requestSpecBuilder -> requestSpecBuilder
                .addHeader("Authorization", "secret"));

            List<ServeEvent> events = wireMockServer.getAllServeEvents();

            Assertions.assertThat(events.size()).isEqualTo(1);

            LoggedRequest loggedRequest = events.get(0).getRequest();
            Assertions.assertThat(loggedRequest.getUrl()).isEqualTo(path + "?key=value");
            Assertions.assertThat(loggedRequest.getMethod()).isEqualTo(RequestMethod.GET);
            Assertions.assertThat(loggedRequest.getHeader("Authorization"))
                .isEqualTo("secret");
        }

        @Test
        void usingBaseUrlPathAndQuery() {
            String path = "/get";

            wireMockServer.stubFor(WireMock.get(path)
                .willReturn(WireMock.aResponse()
                    .withStatus(200)
                )
            );

            var actions = RestAssuredActions.builder()
                .withAuthSupplier(requestSpecBuilder -> requestSpecBuilder
                    .addHeader("Authorization", "secret"))
                .build();

            Map<String, Object> query = Map.of("key", "value");

            actions.get(URL, path, query);

            List<ServeEvent> events = wireMockServer.getAllServeEvents();

            Assertions.assertThat(events.size()).isEqualTo(1);

            LoggedRequest loggedRequest = events.get(0).getRequest();
            Assertions.assertThat(loggedRequest.getUrl()).isEqualTo(path + "?key=value");
            Assertions.assertThat(loggedRequest.getMethod()).isEqualTo(RequestMethod.GET);
            Assertions.assertThat(loggedRequest.getHeader("Authorization"))
                .isEqualTo("secret");
        }
    }

    @Nested
    class Delete {

        @Test
        void usingBaseUrlPathAndAuthSupplier() {
            String path = "/delete";

            wireMockServer.stubFor(WireMock.delete(path)
                .willReturn(WireMock.aResponse()
                    .withStatus(200)
                )
            );

            var actions = RestAssuredActions.builder()
                .build();

            actions.delete(URL, path, requestSpecBuilder -> requestSpecBuilder
                .addHeader("Authorization", "secret"));

            List<ServeEvent> events = wireMockServer.getAllServeEvents();

            Assertions.assertThat(events.size()).isEqualTo(1);

            LoggedRequest loggedRequest = events.get(0).getRequest();
            Assertions.assertThat(loggedRequest.getUrl()).isEqualTo(path);
            Assertions.assertThat(loggedRequest.getMethod()).isEqualTo(RequestMethod.DELETE);
            Assertions.assertThat(loggedRequest.getHeader("Authorization"))
                .isEqualTo("secret");
        }

        @Test
        void usingBaseUrlAndPath() {
            String path = "/delete";

            wireMockServer.stubFor(WireMock.delete(path)
                .willReturn(WireMock.aResponse()
                    .withStatus(200)
                )
            );

            var actions = RestAssuredActions.builder()
                .withAuthSupplier(requestSpecBuilder -> requestSpecBuilder
                    .addHeader("Authorization", "secret"))
                .build();

            actions.delete(URL, path);

            List<ServeEvent> events = wireMockServer.getAllServeEvents();

            Assertions.assertThat(events.size()).isEqualTo(1);

            LoggedRequest loggedRequest = events.get(0).getRequest();
            Assertions.assertThat(loggedRequest.getUrl()).isEqualTo(path);
            Assertions.assertThat(loggedRequest.getMethod()).isEqualTo(RequestMethod.DELETE);
            Assertions.assertThat(loggedRequest.getHeader("Authorization"))
                .isEqualTo("secret");
        }
    }

    @Nested
    class GetAuthSupplier {

        @Test
        void returnsNoAuthSupplierWhenDefaultActions() {
            var actions = RestAssuredActions.builder().build();
            Class<? extends AuthSupplier> actual = actions.getAuthSupplier().getClass();
            Assertions.assertThat(actual).isEqualTo(NoAuthSupplier.class);
        }

        @Test
        void returnsAuthSupplierProvidedDuringBuilding() {
            var authSupplier = new NoAuthSupplier();
            var actions = RestAssuredActions.builder()
                .withAuthSupplier(authSupplier)
                .build();
            Assertions.assertThat(actions.getAuthSupplier()).isSameAs(authSupplier);
        }
    }

    @Nested
    class GetRestAssuredConfig {

        @Test
        void returnsDefaultConfigWhenDefaultActions() {
            var actions = RestAssuredActions.builder().build();
            Class<? extends RestAssuredConfig> actual = actions.getRestAssuredConfig().getClass();
            Assertions.assertThat(actual).isEqualTo(RestAssuredConfig.class);
        }

        @Test
        void returnsConfigProvidedDuringBuilding() {
            var cfg = RestAssuredConfig.config();
            var actions = RestAssuredActions.builder()
                .withRestAssuredConfig(cfg)
                .build();
            Assertions.assertThat(actions.getRestAssuredConfig()).isSameAs(cfg);
        }
    }

    @Nested
    class GetFilters {

        @Test
        void returnsEmptyFiltersListWhenDefaultActions() {
            var actions = RestAssuredActions.builder()
                .build();
            Assertions.assertThat(actions.getFilters()).isEmpty();
        }

        @Test
        void returnsFiltersProvidedDuringBuilding() {
            List<Filter> filters = List.of(new RequestLoggingFilter(), new ResponseLoggingFilter());
            var actions = RestAssuredActions.builder()
                .withFilters(filters)
                .build();
            Assertions.assertThat(actions.getFilters()).isSameAs(filters);
        }
    }
}
