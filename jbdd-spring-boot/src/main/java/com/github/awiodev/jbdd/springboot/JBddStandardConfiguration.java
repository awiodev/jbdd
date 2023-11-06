package com.github.awiodev.jbdd.springboot;

import com.github.awiodev.jbdd.core.definition.JBddRun;
import com.github.awiodev.jbdd.core.definition.JBddSteps;
import com.github.awiodev.jbdd.core.impl.JBdd;
import com.github.awiodev.jbdd.core.impl.JBddStandardSteps;
import com.github.awiodev.jbdd.junit5.JBddExtension;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JBddStandardConfiguration {

    @Bean
    public JBddSteps<?> jBddSteps() {
        return JBddStandardSteps.builder().build();
    }

    @Bean
    public JBddExtension jBddExtension(JBddSteps<?> steps) {
        return JBddExtension.builder()
            .withSetupAndTearDown(() -> JBdd.builder()
                .withSteps(steps)
                .build(), JBddRun::clean)
            .build();
    }
}
