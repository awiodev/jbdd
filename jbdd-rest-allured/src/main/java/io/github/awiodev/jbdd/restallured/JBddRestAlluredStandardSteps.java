package io.github.awiodev.jbdd.restallured;

import io.github.awiodev.jbdd.restassured.definition.AuthSupplier;
import io.github.awiodev.jbdd.restassured.definition.JBddRestAssuredSteps;
import io.qameta.allure.Param;
import io.qameta.allure.Step;
import io.qameta.allure.model.Parameter;
import io.restassured.response.Response;
import java.util.Map;

public class JBddRestAlluredStandardSteps implements JBddRestAssuredSteps {

    private final JBddRestAssuredSteps restAssuredSteps;

    private JBddRestAlluredStandardSteps(JBddRestAssuredSteps restAssuredSteps) {
        this.restAssuredSteps = restAssuredSteps;
    }

    @Step("when POST to {baseUrl}/{path}")
    @Override
    public Response whenPostTo(@Param(mode = Parameter.Mode.HIDDEN) String baseUrl,
                               @Param(mode = Parameter.Mode.HIDDEN) String path,
                               @Param(mode = Parameter.Mode.HIDDEN) Object payload,
                               @Param(mode = Parameter.Mode.HIDDEN) AuthSupplier authSupplier) {
        return restAssuredSteps.whenPostTo(baseUrl, path, payload, authSupplier);
    }

    @Step("when POST to {baseUrl}/{path}")
    @Override
    public Response whenPostTo(@Param(mode = Parameter.Mode.HIDDEN) String baseUrl,
                               @Param(mode = Parameter.Mode.HIDDEN) String path,
                               @Param(mode = Parameter.Mode.HIDDEN) Object payload) {
        return restAssuredSteps.whenPostTo(baseUrl, path, payload);
    }

    @Step("when POST to {baseUrl}/{path}")
    @Override
    public Response whenPostTo(@Param(mode = Parameter.Mode.HIDDEN) String baseUrl,
                               @Param(mode = Parameter.Mode.HIDDEN) String path,
                               @Param(mode = Parameter.Mode.HIDDEN) AuthSupplier authSupplier) {
        return restAssuredSteps.whenPostTo(baseUrl, path, authSupplier);
    }

    @Step("when POST to {baseUrl}/{path}")
    @Override
    public Response whenPostTo(@Param(mode = Parameter.Mode.HIDDEN) String baseUrl,
                               @Param(mode = Parameter.Mode.HIDDEN) String path) {
        return restAssuredSteps.whenPostTo(baseUrl, path);
    }

    @Step("when PUT to {baseUrl}/{path}")
    @Override
    public Response whenPutTo(@Param(mode = Parameter.Mode.HIDDEN) String baseUrl,
                              @Param(mode = Parameter.Mode.HIDDEN) String path,
                              @Param(mode = Parameter.Mode.HIDDEN) Object payload,
                              @Param(mode = Parameter.Mode.HIDDEN) AuthSupplier authSupplier) {
        return restAssuredSteps.whenPutTo(baseUrl, path, payload, authSupplier);
    }

    @Step("when PUT to {baseUrl}/{path}")
    @Override
    public Response whenPutTo(@Param(mode = Parameter.Mode.HIDDEN) String baseUrl,
                              @Param(mode = Parameter.Mode.HIDDEN) String path,
                              @Param(mode = Parameter.Mode.HIDDEN) Object payload) {
        return restAssuredSteps.whenPutTo(baseUrl, path, payload);
    }

    @Step("when PUT to {baseUrl}/{path}")
    @Override
    public Response whenPutTo(@Param(mode = Parameter.Mode.HIDDEN) String baseUrl,
                              @Param(mode = Parameter.Mode.HIDDEN) String path,
                              @Param(mode = Parameter.Mode.HIDDEN) AuthSupplier authSupplier) {
        return restAssuredSteps.whenPutTo(baseUrl, path, authSupplier);
    }

    @Step("when PUT to {baseUrl}/{path}")
    @Override
    public Response whenPutTo(@Param(mode = Parameter.Mode.HIDDEN) String baseUrl,
                              @Param(mode = Parameter.Mode.HIDDEN) String path) {
        return restAssuredSteps.whenPutTo(baseUrl, path);
    }

    @Step("when GET from {baseUrl}/{path}")
    @Override
    public Response whenGetFrom(@Param(mode = Parameter.Mode.HIDDEN) String baseUrl,
                                @Param(mode = Parameter.Mode.HIDDEN) String path,
                                @Param(mode = Parameter.Mode.HIDDEN)
                                Map<String, Object> queryParams,
                                @Param(mode = Parameter.Mode.HIDDEN) AuthSupplier authSupplier) {
        return restAssuredSteps.whenGetFrom(baseUrl, path, queryParams, authSupplier);
    }

    @Step("when GET from {baseUrl}/{path}")
    @Override
    public Response whenGetFrom(@Param(mode = Parameter.Mode.HIDDEN) String baseUrl,
                                @Param(mode = Parameter.Mode.HIDDEN) String path,
                                @Param(mode = Parameter.Mode.HIDDEN)
                                Map<String, Object> queryParams) {
        return restAssuredSteps.whenGetFrom(baseUrl, path, queryParams);
    }

    @Step("when GET from {baseUrl}/{path}")
    @Override
    public Response whenGetFrom(@Param(mode = Parameter.Mode.HIDDEN) String baseUrl,
                                @Param(mode = Parameter.Mode.HIDDEN) String path,
                                @Param(mode = Parameter.Mode.HIDDEN) AuthSupplier authSupplier) {
        return restAssuredSteps.whenGetFrom(baseUrl, path, authSupplier);
    }

    @Step("when GET from {baseUrl}/{path}")
    @Override
    public Response whenGetFrom(@Param(mode = Parameter.Mode.HIDDEN) String baseUrl,
                                @Param(mode = Parameter.Mode.HIDDEN) String path) {
        return restAssuredSteps.whenGetFrom(baseUrl, path);
    }

    @Step("when DELETE from {baseUrl}/{path}")
    @Override
    public Response whenDelete(@Param(mode = Parameter.Mode.HIDDEN) String baseUrl,
                               @Param(mode = Parameter.Mode.HIDDEN) String path,
                               @Param(mode = Parameter.Mode.HIDDEN) AuthSupplier authSupplier) {
        return restAssuredSteps.whenDelete(baseUrl, path, authSupplier);
    }

    @Step("when DELETE from {baseUrl}/{path}")
    @Override
    public Response whenDelete(@Param(mode = Parameter.Mode.HIDDEN) String baseUrl,
                               @Param(mode = Parameter.Mode.HIDDEN) String path) {
        return restAssuredSteps.whenDelete(baseUrl, path);
    }

    @Step("when PATCH {baseUrl}/{path}")
    @Override
    public Response whenPatch(@Param(mode = Parameter.Mode.HIDDEN) String baseUrl,
                              @Param(mode = Parameter.Mode.HIDDEN) String path,
                              @Param(mode = Parameter.Mode.HIDDEN) Object payload,
                              @Param(mode = Parameter.Mode.HIDDEN) AuthSupplier authSupplier) {
        return restAssuredSteps.whenPatch(baseUrl, path, payload, authSupplier);
    }

    @Step("when PATCH {baseUrl}/{path}")
    @Override
    public Response whenPatch(@Param(mode = Parameter.Mode.HIDDEN) String baseUrl,
                              @Param(mode = Parameter.Mode.HIDDEN) String path,
                              @Param(mode = Parameter.Mode.HIDDEN) Object payload) {
        return restAssuredSteps.whenPatch(baseUrl, path, payload);
    }

    @Step("then response code is {expectedStatus}")
    @Override
    public void thenResponseStatusIs(@Param(mode = Parameter.Mode.HIDDEN) Response response,
                                     @Param(mode = Parameter.Mode.HIDDEN) int expectedStatus) {
        restAssuredSteps.thenResponseStatusIs(response, expectedStatus);
    }

    @Step("then response deserializes to {expectedResponseType}")
    @Override
    public <T> T thenResponseDeserializesTo(@Param(mode = Parameter.Mode.HIDDEN) Response response,
                                            @Param(mode = Parameter.Mode.HIDDEN)
                                            Class<T> expectedResponseType) {
        return restAssuredSteps.thenResponseDeserializesTo(response, expectedResponseType);
    }

    public static JBddRestAlluredStandardStepsBuilder builder() {
        return new JBddRestAlluredStandardStepsBuilder();
    }

    public static final class JBddRestAlluredStandardStepsBuilder {
        private JBddRestAssuredSteps restAssuredSteps;

        private JBddRestAlluredStandardStepsBuilder() {
        }

        public JBddRestAlluredStandardStepsBuilder withRestAssuredSteps(
            JBddRestAssuredSteps restAssuredSteps) {
            this.restAssuredSteps = restAssuredSteps;
            return this;
        }

        public JBddRestAlluredStandardSteps build() {
            return new JBddRestAlluredStandardSteps(restAssuredSteps);
        }
    }
}
