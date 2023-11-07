package io.github.awiodev.jbdd.springboot.custom;

import io.github.awiodev.jbdd.core.impl.JBdd;
import io.github.awiodev.jbdd.junit5.JBddExtension;
import io.github.awiodev.jbdd.springboot.custom.impl.JBddCustomStepConfiguration;
import io.github.awiodev.jbdd.springboot.custom.impl.JBddCustomSteps;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = JBddCustomStepConfiguration.class)
public class JBddCustomStepConfigurationTest {

    @RegisterExtension
    @Autowired
    public JBddExtension extension;

    @Test
    void jBddRunHasCustomStepsInjected(JBdd jBdd) {
        Assertions.assertThat(jBdd.scenario().getClass().getName())
            .describedAs("Invalid steps class injected")
            .isEqualTo(JBddCustomSteps.class.getName());
    }
}
