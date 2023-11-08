package io.github.awiodev.jbdd.springboot.custom.impl;

import io.github.awiodev.jbdd.core.definition.JBddSteps;
import io.github.awiodev.jbdd.springboot.JBddStandardConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JBddCustomStepConfiguration extends JBddStandardConfiguration {

    @Override
    @Bean
    public JBddSteps<?> jBddSteps() {
        return new JBddCustomSteps();
    }
}
