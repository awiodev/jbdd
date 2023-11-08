package io.github.awiodev.jbdd.examples.springboot;

import com.github.javafaker.Faker;
import com.github.tomakehurst.wiremock.WireMockServer;
import io.github.awiodev.jbdd.allure.JBddAllureSteps;
import io.github.awiodev.jbdd.core.definition.JBddContextFactory;
import io.github.awiodev.jbdd.core.definition.JBddRun;
import io.github.awiodev.jbdd.core.definition.JBddSteps;
import io.github.awiodev.jbdd.core.impl.JBddStandardContextFactory;
import io.github.awiodev.jbdd.examples.restassured.custom.JBddUserApiRun;
import io.github.awiodev.jbdd.examples.restassured.steps.FakeUserServiceSteps;
import io.github.awiodev.jbdd.examples.restassured.steps.UserApiRestSteps;
import io.github.awiodev.jbdd.examples.restassured.steps.UserApiSteps;
import io.github.awiodev.jbdd.junit5.JBddExtension;
import io.github.awiodev.jbdd.restallured.AllureRequestLogFilter;
import io.github.awiodev.jbdd.restallured.AllureResponseLogFilter;
import io.github.awiodev.jbdd.restallured.JBddRestAlluredStandardSteps;
import io.github.awiodev.jbdd.restassured.impl.JBddRestAssuredStandardSteps;
import io.github.awiodev.jbdd.restassured.impl.RestAssuredActions;
import io.github.awiodev.jbdd.springboot.JBddStandardConfiguration;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import java.util.List;
import java.util.Random;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JBddRestAlluredConfiguration extends JBddStandardConfiguration {

    private static final int USER_SERVICE_PORT = 8094;

    private static final String USER_SERVICE_BASE_URL =
        String.format("http://localhost:%s", USER_SERVICE_PORT);

    @Bean
    Faker faker() {
        return new Faker(new Random(24));
    }

    @Bean(destroyMethod = "stop")
    WireMockServer wireMockServer() {
        return new WireMockServer(USER_SERVICE_PORT);
    }

    @Bean
    FakeUserServiceSteps fakeUserServiceSteps(WireMockServer wireMockServer, Faker faker) {
        return new FakeUserServiceSteps(wireMockServer, faker);
    }

    @Bean
    UserApiSteps userApiSteps() {
        return UserApiRestSteps.builder()
            .withBaseUrl(USER_SERVICE_BASE_URL)
            .withRestAssuredSteps(JBddRestAlluredStandardSteps.builder()
                .withRestAssuredSteps(JBddRestAssuredStandardSteps.builder()
                    .withRestAssuredActions(RestAssuredActions.builder()
                        .withFilters(
                            List.of(new RequestLoggingFilter(), new ResponseLoggingFilter(),
                                new AllureRequestLogFilter(), new AllureResponseLogFilter()))
                        .build())
                    .build())
                .build())
            .build();
    }

    @Override
    @Bean
    public JBddSteps<?> jBddSteps() {
        return JBddAllureSteps.builder().build();
    }

    @Bean
    public JBddExtension jBddExtension(JBddSteps<?> steps, JBddContextFactory<?> contextFactory,
                                       FakeUserServiceSteps fakeUserServiceSteps,
                                       UserApiSteps userApiSteps, Faker faker) {
        return JBddExtension.builder()
            .withSetupAndTearDown(() -> JBddUserApiRun.builder()
                    .withJBddSteps(steps)
                    .withJBddStandardContextFactory((JBddStandardContextFactory) contextFactory)
                    .withFakeUserServiceSteps(fakeUserServiceSteps)
                    .withApiSteps(userApiSteps)
                    .withFaker(faker)
                    .build(),
                JBddRun::clean)
            .build();
    }
}
