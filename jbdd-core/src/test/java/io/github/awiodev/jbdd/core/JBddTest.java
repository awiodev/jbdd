package io.github.awiodev.jbdd.core;

import io.github.awiodev.jbdd.core.exceptions.NotFoundException;
import io.github.awiodev.jbdd.core.impl.JBdd;
import io.github.awiodev.jbdd.core.impl.JBddStandardSteps;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class JBddTest {

    private final JBddStandardSteps steps = JBddStandardSteps.builder().build();

    @Nested
    class Scenario {

        @Test
        void returnsStepsObjectWhenCalled() {
            var run = JBdd.builder().withSteps(steps).build();
            Assertions.assertThat(run.scenario()).isEqualTo(steps);
        }
    }

    @Nested
    class Context {

        @Test
        void returnsStepsObjectWhenCalled() {
            var run = JBdd.builder()
                .withSteps(steps)
                .build();
            Assertions.assertThat(run.context()).isNotNull();
        }
    }

    @Nested
    class Clean {

        @Test
        void cleansUpContextData() {
            JBdd run = JBdd.builder().build();
            run.context().store("key", "value");
            run.clean();

            Assertions.assertThatThrownBy(() ->
                    run.context().get("key", String.class))
                .isInstanceOf(NotFoundException.class);
        }
    }

    @Nested
    class Builder {

        @Test
        void createsDefaultStepsWhenNotProvided() {
            JBdd run = JBdd.builder().build();
            Assertions.assertThat(run.scenario()).isNotNull();
        }
    }
}
