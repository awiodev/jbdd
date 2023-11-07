package io.github.awiodev.jbdd.junit5;

import io.github.awiodev.jbdd.core.impl.JBdd;
import io.github.awiodev.jbdd.core.impl.JBddStandardSteps;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

public class JBddManualExtensionRegistrationTest {

    private final JBddStandardSteps jBddStandardSteps = JBddStandardSteps.builder().build();
    private final List<String> events = new ArrayList<>();

    @RegisterExtension
    private final JBddExtension extension = JBddExtension.builder()
        .withSetupAndTearDown(() -> {
            events.add("start");
            return JBdd.builder().withSteps(jBddStandardSteps).build();
        }, run -> {
            events.add("stop");
            run.clean();
        })
        .build();

    @Nested
    class ExtensionRegistered {

        @Test
        void whenRegisteredUsingFieldAnnotation(JBdd jBdd) {
            Assertions.assertThat(jBdd).isNotNull();
            Assertions.assertThat(jBdd.scenario()).isNotNull();
            Assertions.assertThat(events.size()).isEqualTo(1);
        }
    }
}
