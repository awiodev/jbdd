package com.github.awiodev.jbdd.core;

import com.github.awiodev.jbdd.core.impl.JBddStandardSteps;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class JBddStandardStepsTest {

    @Nested
    class WithJBddStandardSteps {
        @Test
        void itIsPossibleToChain() {
            var steps = JBddStandardSteps.builder().build();

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
            var steps = JBddStandardSteps.builder().build();
            int returned = steps.given("I have a number", () -> 10);
            Assertions.assertThat(returned).isEqualTo(10);
        }

        @Test
        void whenStepCanReturnValue() {
            var steps = JBddStandardSteps.builder().build();
            int returned = steps.when("I create a number", () -> 10);
            Assertions.assertThat(returned).isEqualTo(10);
        }

        @Test
        void thenStepCanReturnValue() {
            var steps = JBddStandardSteps.builder().build();
            int returned = steps.then("I receive a number", () -> 10);
            Assertions.assertThat(returned).isEqualTo(10);
        }

        @Test
        void andStepCanReturnValue() {
            var steps = JBddStandardSteps.builder().build();
            int returned = steps.and("I receive a number", () -> 10);
            Assertions.assertThat(returned).isEqualTo(10);
        }
    }
}
