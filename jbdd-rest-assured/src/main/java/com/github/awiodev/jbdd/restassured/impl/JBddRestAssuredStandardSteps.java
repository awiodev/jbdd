package com.github.awiodev.jbdd.restassured.impl;

import com.github.awiodev.jbdd.restassured.definition.AuthSupplier;
import com.github.awiodev.jbdd.restassured.definition.JBddRestAssuredSteps;
import io.restassured.response.Response;
import java.util.Map;
import org.assertj.core.api.Assertions;

/**
 * Rest assured steps abstraction.
 */
public class JBddRestAssuredStandardSteps implements JBddRestAssuredSteps {

    private final RestAssuredActions restAssuredActions;

    private JBddRestAssuredStandardSteps(RestAssuredActions restAssuredActions) {
        this.restAssuredActions = restAssuredActions;
    }

    public Response whenPostTo(String baseUrl, String path, Object payload,
                               AuthSupplier authSupplier) {

        return restAssuredActions.postJson(baseUrl, path, payload, authSupplier);
    }

    public Response whenPostTo(String baseUrl, String path, Object payload) {

        return restAssuredActions.postJson(baseUrl, path, payload);
    }

    public Response whenPostTo(String baseUrl, String path, AuthSupplier authSupplier) {

        return restAssuredActions.post(baseUrl, path, authSupplier);
    }

    public Response whenPostTo(String baseUrl, String path) {

        return restAssuredActions.post(baseUrl, path);
    }

    public Response whenPutTo(String baseUrl, String path, Object payload,
                              AuthSupplier authSupplier) {

        return restAssuredActions.putJson(baseUrl, path, payload, authSupplier);
    }

    public Response whenPutTo(String baseUrl, String path, Object payload) {

        return restAssuredActions.putJson(baseUrl, path, payload);
    }

    public Response whenPutTo(String baseUrl, String path, AuthSupplier authSupplier) {

        return restAssuredActions.put(baseUrl, path, authSupplier);
    }

    public Response whenPutTo(String baseUrl, String path) {

        return restAssuredActions.put(baseUrl, path);
    }

    public Response whenGetFrom(String baseUrl, String path, Map<String, Object> queryParams,
                                AuthSupplier authSupplier) {

        return restAssuredActions.get(baseUrl, path, queryParams, authSupplier);
    }

    public Response whenGetFrom(String baseUrl, String path, Map<String, Object> queryParams) {

        return restAssuredActions.get(baseUrl, path, queryParams);
    }

    public Response whenGetFrom(String baseUrl, String path, AuthSupplier authSupplier) {

        return restAssuredActions.get(baseUrl, path, authSupplier);
    }

    public Response whenGetFrom(String baseUrl, String path) {

        return restAssuredActions.get(baseUrl, path);
    }

    public Response whenDelete(String baseUrl, String path, AuthSupplier authSupplier) {

        return restAssuredActions.delete(baseUrl, path, authSupplier);
    }

    public Response whenDelete(String baseUrl, String path) {

        return restAssuredActions.delete(baseUrl, path);
    }

    public Response whenPatch(String baseUrl, String path, Object payload,
                              AuthSupplier authSupplier) {

        return restAssuredActions.patch(baseUrl, path, payload, authSupplier);
    }

    public Response whenPatch(String baseUrl, String path, Object payload) {

        return restAssuredActions.patch(baseUrl, path, payload);
    }

    @Override
    public void thenResponseStatusIs(Response response, int expectedStatus) {
        Assertions.assertThat(response.statusCode())
            .describedAs("Response status code is not valid!").isEqualTo(expectedStatus);
    }

    @Override
    public <T> T thenResponseDeserializesTo(Response response, Class<T> expectedResponseType) {
        return response.as(expectedResponseType);
    }
}
