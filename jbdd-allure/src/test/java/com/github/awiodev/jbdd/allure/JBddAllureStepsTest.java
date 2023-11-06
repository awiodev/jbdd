package com.github.awiodev.jbdd.allure;

import com.github.awiodev.jbdd.core.definition.JBddRun;
import com.github.awiodev.jbdd.core.impl.JBdd;
import com.github.awiodev.jbdd.core.impl.JBddStandardContextFactory;
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
            var steps = JBddAllureSteps.builder().build();

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
            var steps = JBddAllureSteps.builder().build();
            int returned = steps.given("I have a number", () -> 10);
            Assertions.assertThat(returned).isEqualTo(10);
        }

        @Test
        void whenStepCanReturnValue() {
            var steps = JBddAllureSteps.builder().build();
            int returned = steps.when("I create a number", () -> 10);
            Assertions.assertThat(returned).isEqualTo(10);
        }

        @Test
        void thenStepCanReturnValue() {
            var steps = JBddAllureSteps.builder().build();
            int returned = steps.then("I receive a number", () -> 10);
            Assertions.assertThat(returned).isEqualTo(10);
        }

        @Test
        void andStepCanReturnValue() {
            var steps = JBddAllureSteps.builder().build();
            int returned = steps.and("I receive a number", () -> 10);
            Assertions.assertThat(returned).isEqualTo(10);
        }
    }

    private final JBddAllureSteps steps = JBddAllureSteps.builder().build();

    @RegisterExtension
    private final JBddExtension extension = JBddExtension.builder()
        .withSetupAndTearDown(() -> JBdd.builder()
                .withSteps(steps)
                .withContextFactory(JBddStandardContextFactory.builder().build())
                .build(),
            JBddRun::clean)
        .build();

    @Nested
    class AllureStepsAreInjected {

        @Test
        void whenProvidedDuringRegistration(JBdd jBdd) {
            Assertions.assertThat(jBdd.scenario()).isEqualTo(steps);
        }
    }
}
