package com.github.awiodev.jbdd.springboot.custom.impl;

import com.github.awiodev.jbdd.core.definition.JBddRun;
import com.github.awiodev.jbdd.core.impl.JBddStandardContext;

public class JBddCustomRun implements JBddRun<JBddCustomSteps, JBddStandardContext> {

    private final JBddCustomSteps scenario;

    private final JBddStandardContext jBddStandardContext;

    private final JBddBusinessSteps jBddBusinessSteps;

    public JBddCustomRun(JBddCustomSteps scenario,
                         JBddBusinessSteps jBddBusinessSteps) {
        this.scenario = scenario;
        this.jBddStandardContext = JBddStandardContext.builder().build();
        this.jBddBusinessSteps = jBddBusinessSteps;
    }

    @Override
    public JBddCustomSteps scenario() {
        return scenario;
    }

    @Override
    public JBddStandardContext context() {
        return jBddStandardContext;
    }

    public JBddBusinessSteps businessSteps() {
        return jBddBusinessSteps;
    }

    @Override
    public void clean() {
        jBddStandardContext.cleanup();
    }
}
