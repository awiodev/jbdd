package io.github.awiodev.jbdd.restassured.definition;

import io.github.awiodev.jbdd.core.definition.Steps;
import io.restassured.response.Response;
import java.util.Map;

/**
 * JBdd steps for rest assured actions
 */
public interface JBddRestAssuredSteps extends Steps {

    Response whenPostTo(String baseUrl, String path, Object payload,
                        AuthSupplier authSupplier);

    Response whenPostTo(String baseUrl, String path, Object payload);

    Response whenPostTo(String baseUrl, String path, AuthSupplier authSupplier);

    Response whenPostTo(String baseUrl, String path);

    Response whenPutTo(String baseUrl, String path, Object payload,
                       AuthSupplier authSupplier);

    Response whenPutTo(String baseUrl, String path, Object payload);

    Response whenPutTo(String baseUrl, String path, AuthSupplier authSupplier);

    Response whenPutTo(String baseUrl, String path);

    Response whenGetFrom(String baseUrl, String path, Map<String, Object> queryParams,
                         AuthSupplier authSupplier);

    Response whenGetFrom(String baseUrl, String path, Map<String, Object> queryParams);

    Response whenGetFrom(String baseUrl, String path, AuthSupplier authSupplier);

    Response whenGetFrom(String baseUrl, String path);

    Response whenDelete(String baseUrl, String path, AuthSupplier authSupplier);

    Response whenDelete(String baseUrl, String path);

    Response whenPatch(String baseUrl, String path, Object payload,
                              AuthSupplier authSupplier);

    Response whenPatch(String baseUrl, String path, Object payload);

    /**
     * Performs assertion of status code
     * @param response as API call result
     * @param expectedStatus as expected HTTP status code
     */
    void thenResponseStatusIs(Response response, int expectedStatus);

    /**
     * Performs response deserialization. Should throw an exception when object cannot be deserialized to given type.
     * @param response as API call result
     * @param expectedResponseType as expected response body type
     * @return instance of deserialized object
     * @param <T> as response type
     */
    <T> T thenResponseDeserializesTo(Response response, Class<T> expectedResponseType);
}
