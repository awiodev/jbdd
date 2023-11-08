package io.github.awiodev.jbdd.junit5;

import io.github.awiodev.jbdd.core.impl.JBdd;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(JBddExtension.class)
public class JBddAutomaticExtensionRegistrationTest {

    @Nested
    class ExtensionRegistered {

        @Test
        void whenRegisteredUsingClassAnnotation(JBdd jBdd) {
            Assertions.assertThat(jBdd).isNotNull();
            Assertions.assertThat(jBdd.scenario()).isNotNull();
        }
    }
}
