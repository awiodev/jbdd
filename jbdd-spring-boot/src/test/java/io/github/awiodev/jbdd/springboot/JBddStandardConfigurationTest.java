package io.github.awiodev.jbdd.springboot;

import io.github.awiodev.jbdd.core.impl.JBdd;
import io.github.awiodev.jbdd.junit5.JBddExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = JBddStandardConfiguration.class)
public class JBddStandardConfigurationTest {

    @RegisterExtension
    @Autowired
    public JBddExtension extension;

    @Test
    void jBddRunIsInjected(JBdd jBdd) {
        jBdd.scenario().when("I try to use injected steps", () -> {
            // nothing to do here
        }).then("Test passes without error", () -> {
            // nor here
        });
    }
}
