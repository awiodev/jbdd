package io.github.awiodev.jbdd.springboot;

import io.github.awiodev.jbdd.core.definition.JBddContextFactory;
import io.github.awiodev.jbdd.core.definition.JBddRun;
import io.github.awiodev.jbdd.core.definition.JBddSteps;
import io.github.awiodev.jbdd.core.impl.JBdd;
import io.github.awiodev.jbdd.core.impl.JBddStandardContextFactory;
import io.github.awiodev.jbdd.core.impl.JBddStandardSteps;
import io.github.awiodev.jbdd.junit5.JBddExtension;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JBddStandardConfiguration {

    @Bean
    public JBddContextFactory<?> jBddContextFactory() {
        return JBddStandardContextFactory.builder().build();
    }

    @Bean
    public JBddSteps<?> jBddSteps() {
        return JBddStandardSteps.builder().build();
    }

    @Bean
    public JBddExtension jBddExtension(JBddSteps<?> steps, JBddContextFactory<?> contextFactory) {
        return JBddExtension.builder()
            .withSetupAndTearDown(() -> JBdd.builder()
                .withSteps(steps)
                .withContextFactory(contextFactory)
                .build(), JBddRun::clean)
            .build();
    }
}
