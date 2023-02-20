package com.github.awiodev.jbdd.allure;

import com.github.awiodev.jbdd.core.JBddBaseRun;
import com.github.awiodev.jbdd.core.JBddRun;
import com.github.awiodev.jbdd.junit5.JBddExtension;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

public class JBddAllureStepsTest {

    @Nested
    class WithJBddAllureSteps {
        @Test
        void itIsPossibleToChain() {
            var steps = new JBddAllureBaseSteps();

            steps.given("My run just started", () -> {
                // No step implementation required
            }).and("I know steps order", () -> {
                // No step implementation required
            }).when("I create scenario", () -> {
                // No step implementation required
            }).then("My steps are chained", () -> {
                // No step implementation required
            });
        }

        @Test
        void givenStepCanReturnValue() {
            var steps = new JBddAllureBaseSteps();
            int returned = steps.given("I have a number", () -> 10);
            Assertions.assertThat(returned).isEqualTo(10);
        }

        @Test
        void whenStepCanReturnValue() {
            var steps = new JBddAllureBaseSteps();
            int returned = steps.when("I create a number", () -> 10);
            Assertions.assertThat(returned).isEqualTo(10);
        }

        @Test
        void thenStepCanReturnValue() {
            var steps = new JBddAllureBaseSteps();
            int returned = steps.then("I receive a number", () -> 10);
            Assertions.assertThat(returned).isEqualTo(10);
        }

        @Test
        void andStepCanReturnValue() {
            var steps = new JBddAllureBaseSteps();
            int returned = steps.and("I receive a number", () -> 10);
            Assertions.assertThat(returned).isEqualTo(10);
        }
    }

    private final JBddAllureBaseSteps steps = new JBddAllureBaseSteps();

    @RegisterExtension
    private final JBddExtension extension = JBddExtension.builder()
            .withSteps(steps)
            .withSetup(() -> new JBddRun(steps))
            .withTeardown(JBddBaseRun::cleanup)
            .build();

    @Nested
    class AllureStepsAreInjected {

        @Test
        void whenProvidedDuringRegistration(JBddRun jBdd) {
            Assertions.assertThat(jBdd.scenario()).isEqualTo(steps);
        }
    }
}
