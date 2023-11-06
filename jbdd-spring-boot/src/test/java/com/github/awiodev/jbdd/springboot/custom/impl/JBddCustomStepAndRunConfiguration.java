package com.github.awiodev.jbdd.springboot.custom.impl;

import com.github.awiodev.jbdd.core.definition.JBddRun;
import com.github.awiodev.jbdd.core.definition.JBddSteps;
import com.github.awiodev.jbdd.core.impl.JBdd;
import com.github.awiodev.jbdd.junit5.JBddExtension;
import com.github.awiodev.jbdd.springboot.JBddStandardConfiguration;
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
    public JBddExtension jBddExtension(JBddSteps<?> steps) {

        var customRun = new JBddCustomRun((JBddCustomSteps) steps, new JBddBusinessSteps());

        return JBddExtension.builder()
            .withSetupAndTearDown(() -> customRun, JBddRun::clean)
            .build();
    }
}
