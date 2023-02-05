package com.github.awiodev.jbdd.junit5;

import com.github.awiodev.jbdd.core.JBddSteps;
import com.github.awiodev.jbdd.core.JBddRun;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.util.ArrayList;
import java.util.List;

public class JBddManualExtensionRegistrationTest {

    private final JBddSteps jBddSteps = new JBddSteps();
    private final List<String> events = new ArrayList<>();

    @RegisterExtension
    private final JBddExtension extension = JBddExtension.builder()
            .withSteps(jBddSteps)
            .withSetup(() -> {
                events.add("start");
                return new JBddRun(jBddSteps);
            }).withTeardown(run -> {
                events.add("stop");
                run.cleanup();
            })
            .build();

    @Nested
    class ExtensionRegistered {

        @Test
        void whenRegisteredUsingFieldAnnotation(JBddRun jBdd) {
            Assertions.assertThat(jBdd).isNotNull();
            Assertions.assertThat(jBdd.steps()).isNotNull();
            Assertions.assertThat(events.size()).isEqualTo(1);
        }
    }
}
