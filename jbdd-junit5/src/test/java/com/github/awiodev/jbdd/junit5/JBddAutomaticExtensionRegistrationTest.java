package com.github.awiodev.jbdd.junit5;

import com.github.awiodev.jbdd.core.JBddRun;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(JBddExtension.class)
public class JBddAutomaticExtensionRegistrationTest {

    @Nested
    class ExtensionRegistered {

        @Test
        void whenRegisteredUsingClassAnnotation(JBddRun jBdd) {
            Assertions.assertThat(jBdd).isNotNull();
            Assertions.assertThat(jBdd.steps()).isNotNull();
        }
    }
}
