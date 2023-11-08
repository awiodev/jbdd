package io.github.awiodev.jbdd.springboot.custom.impl;

import io.github.awiodev.jbdd.core.definition.JBddContextFactory;
import io.github.awiodev.jbdd.core.definition.JBddRun;
import io.github.awiodev.jbdd.core.definition.JBddSteps;
import io.github.awiodev.jbdd.core.impl.JBddStandardContextFactory;
import io.github.awiodev.jbdd.junit5.JBddExtension;
import io.github.awiodev.jbdd.springboot.JBddStandardConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JBddCustomStepAndRunConfiguration extends JBddStandardConfiguration {

    @Override
    @Bean
    public JBddSteps<?> jBddSteps() {
        return new JBddCustomSteps();
    }

    @Override
    @Bean
    public JBddExtension jBddExtension(JBddSteps<?> steps, JBddContextFactory<?> contextFactory) {

        var customRun = new JBddCustomRun((JBddCustomSteps) steps, new JBddBusinessSteps(),
            (JBddStandardContextFactory) contextFactory);

        return JBddExtension.builder()
            .withSetupAndTearDown(() -> customRun, JBddRun::clean)
            .build();
    }
}
