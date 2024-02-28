package io.github.awiodev.jbdd.restassured.impl;

import io.github.awiodev.jbdd.restassured.definition.AuthSupplier;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.Filter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * Wrapped rest assured actions
 */
public final class RestAssuredActions {
    private final AuthSupplier authSupplier;
    private final RestAssuredConfig restAssuredConfig;
    private final List<Filter> filters;

    private RestAssuredActions(AuthSupplier authSupplier,
                               RestAssuredConfig restAssuredConfig,
                               List<Filter> filters) {
        this.authSupplier = authSupplier;
        this.restAssuredConfig = restAssuredConfig;
        this.filters = filters;
    }

    public RequestSpecBuilder createDefaultSpecBuilder(String baseUrl, String path,
                                                       AuthSupplier authSupplier) {
        RequestSpecBuilder specBuilder = new RequestSpecBuilder()
            .setConfig(restAssuredConfig)
            .setBaseUri(baseUrl)
            .setBasePath(path)
            .addFilters(filters);
        authSupplier.supply(specBuilder);
        return specBuilder;
    }

    public RequestSpecBuilder createJsonPayloadSpecBuilder(String baseUrl, String path,
                                                           Object payload,
                                                           AuthSupplier authSupplier) {
        RequestSpecBuilder specBuilder = new RequestSpecBuilder()
            .setConfig(restAssuredConfig)
            .setBaseUri(baseUrl)
            .setBasePath(path)
            .addFilters(filters)
            .setContentType(ContentType.JSON)
            .setBody(payload);
        authSupplier.supply(specBuilder);
        return specBuilder;
    }

    public Response postJson(String baseUrl, String path, Object payload,
                             AuthSupplier authSupplier) {
        RequestSpecification spec =
            createJsonPayloadSpecBuilder(baseUrl, path, payload, authSupplier).build();
        return executePost(spec);
    }

    public Response postJson(String baseUrl, String path, Object payload) {
        return postJson(baseUrl, path, payload, authSupplier);
    }

    public Response post(String baseUrl, String path) {
        return post(baseUrl, path, authSupplier);
    }

    public Response post(String baseUrl, String path, AuthSupplier authSupplier) {
        RequestSpecification spec = createDefaultSpecBuilder(baseUrl, path, authSupplier).build();
        return executePost(spec);
    }

    public Response putJson(String baseUrl, String path, Object payload,
                            AuthSupplier authSupplier) {
        RequestSpecification spec =
            createJsonPayloadSpecBuilder(baseUrl, path, payload, authSupplier).build();
        return executePut(spec);
    }

    public Response putJson(String baseUrl, String path, Object payload) {
        return putJson(baseUrl, path, payload, authSupplier);
    }

    public Response put(String baseUrl, String path, AuthSupplier authSupplier) {
        RequestSpecification spec = createDefaultSpecBuilder(baseUrl, path, authSupplier).build();
        return executePut(spec);
    }

    public Response put(String baseUrl, String path) {
        return put(baseUrl, path, authSupplier);
    }

    public Response get(String baseUrl, String path, Map<String, Object> queryParams,
                        AuthSupplier authSupplier) {
        RequestSpecification specification = createDefaultSpecBuilder(baseUrl, path, authSupplier)
            .addQueryParams(queryParams)
            .build();
        return executeGet(specification);
    }

    public Response get(String baseUrl, String path, Map<String, Object> queryParams) {
        return get(baseUrl, path, queryParams, authSupplier);
    }

    public Response get(String baseUrl, String path, AuthSupplier authSupplier) {
        return get(baseUrl, path, new HashMap<>(), authSupplier);
    }

    public Response get(String baseUrl, String path) {
        return get(baseUrl, path, authSupplier);
    }

    public Response delete(String baseUrl, String path, AuthSupplier authSupplier) {
        RequestSpecification specification =
            createDefaultSpecBuilder(baseUrl, path, authSupplier).build();
        return executeDelete(specification);
    }

    public Response delete(String baseUrl, String path) {
        return delete(baseUrl, path, authSupplier);
    }

    public Response patch(String baseUrl, String path, Object payload, AuthSupplier authSupplier) {
        RequestSpecification spec =
            createJsonPayloadSpecBuilder(baseUrl, path, payload, authSupplier).build();
        return executePatch(spec);
    }

    public Response patch(String baseUrl, String path, Object payload) {
        return patch(baseUrl, path, payload, authSupplier);
    }

    public Response executePost(RequestSpecification spec) {
        return RestAssured.given(spec)
            .when()
            .post()
            .then()
            .extract()
            .response();
    }

    public Response executeGet(RequestSpecification spec) {
        return RestAssured.given(spec)
            .when()
            .get()
            .then()
            .extract()
            .response();
    }

    public Response executePut(RequestSpecification spec) {
        return RestAssured.given(spec)
            .when()
            .put()
            .then()
            .extract()
            .response();
    }

    public Response executeDelete(RequestSpecification spec) {
        return RestAssured.given(spec)
            .when()
            .delete()
            .then()
            .extract()
            .response();
    }

    public Response executePatch(RequestSpecification spec) {
        return RestAssured.given(spec)
            .when()
            .patch()
            .then()
            .extract()
            .response();
    }

    public AuthSupplier getAuthSupplier() {
        return authSupplier;
    }

    public RestAssuredConfig getRestAssuredConfig() {
        return restAssuredConfig;
    }

    public List<Filter> getFilters() {
        return filters;
    }

    public static RestAssuredActionsBuilder builder() {
        return new RestAssuredActionsBuilder();
    }

    public static final class RestAssuredActionsBuilder {
        private AuthSupplier authSupplier;
        private RestAssuredConfig restAssuredConfig;
        private List<Filter> filters;

        private RestAssuredActionsBuilder() {
        }

        public RestAssuredActionsBuilder withAuthSupplier(AuthSupplier authSupplier) {
            this.authSupplier = authSupplier;
            return this;
        }

        public RestAssuredActionsBuilder withRestAssuredConfig(
            RestAssuredConfig restAssuredConfig) {
            this.restAssuredConfig = restAssuredConfig;
            return this;
        }

        public RestAssuredActionsBuilder withFilters(List<Filter> filters) {
            this.filters = filters;
            return this;
        }

        public RestAssuredActions build() {

            if (authSupplier == null) {
                authSupplier = new NoAuthSupplier();
            }

            if (restAssuredConfig == null) {
                restAssuredConfig = RestAssuredConfig.config();
            }

            if (filters == null) {
                filters = new ArrayList<>();
            }

            return new RestAssuredActions(authSupplier, restAssuredConfig, filters);
        }
    }
}
