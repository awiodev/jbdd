package com.github.awiodev.jbdd.springboot.custom;

import com.github.awiodev.jbdd.junit5.JBddExtension;
import com.github.awiodev.jbdd.springboot.custom.impl.JBddCustomRun;
import com.github.awiodev.jbdd.springboot.custom.impl.JBddCustomStepAndRunConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = JBddCustomStepAndRunConfiguration.class)
public class JBddCustomStepAndRunConfigurationTest {

    @RegisterExtension
    @Autowired
    public JBddExtension extension;

    @Test
    void customJBddRunHasCustomStepsInjected(JBddCustomRun jBdd) {
        jBdd.scenario().when("business method is called", () -> {
            jBdd.businessSteps().givenBusinessMethod();
        }).then("no exception is thrown", () -> {});
    }
}
