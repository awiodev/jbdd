package com.github.awiodev.jbdd.restassured.impl;

import com.github.awiodev.jbdd.core.definition.Steps;
import com.github.awiodev.jbdd.restassured.definition.AuthSupplier;
import io.restassured.response.Response;
import java.util.Map;

/**
 * Rest assured steps abstraction.
 */
public class JBddRestAssuredSteps implements Steps {

    private final RestAssuredActions restAssuredActions;

    private JBddRestAssuredSteps(RestAssuredActions restAssuredActions) {
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

    public Response whenPath(String baseUrl, String path, Object payload,
                             AuthSupplier authSupplier) {

        return restAssuredActions.patch(baseUrl, path, payload, authSupplier);
    }

    public Response whenPath(String baseUrl, String path, Object payload) {

        return restAssuredActions.patch(baseUrl, path, payload);
    }
}
