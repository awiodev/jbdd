package io.github.awiodev.jbdd.restallured;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.github.awiodev.jbdd.restassured.impl.JBddRestAssuredStandardSteps;
import io.github.awiodev.jbdd.restassured.impl.RestAssuredActions;
import io.restassured.response.Response;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AllureRequestResponseLogFiltersTest {

    private static final int PORT = 8088;
    private static final String URL = String.format("http://localhost:%s", PORT);
    private final WireMockServer wireMockServer = new WireMockServer(PORT);

    @BeforeEach
    void beforeEach() {
        wireMockServer.start();
    }

    @AfterEach
    void afterEach() {
        wireMockServer.stop();
    }

    @Test
    void requestAndResponseFiltersCanBeApplied() {
        JBddRestAlluredStandardSteps steps = JBddRestAlluredStandardSteps.builder()
            .withRestAssuredSteps(JBddRestAssuredStandardSteps.builder()
                .withRestAssuredActions(RestAssuredActions.builder()
                    .withFilters(
                        List.of(new AllureRequestLogFilter(), new AllureResponseLogFilter()))
                    .build())
                .build())
            .build();

        steps.whenGetFrom(URL, "/hello");
    }
}
