package io.github.awiodev.jbdd.springboot.custom.impl;

import io.github.awiodev.jbdd.core.definition.JBddRun;
import io.github.awiodev.jbdd.core.impl.JBddStandardContext;
import io.github.awiodev.jbdd.core.impl.JBddStandardContextFactory;

public class JBddCustomRun implements JBddRun<JBddCustomSteps, JBddStandardContext> {

    private final JBddCustomSteps scenario;

    private final JBddStandardContext jBddStandardContext;

    private final JBddBusinessSteps jBddBusinessSteps;

    public JBddCustomRun(JBddCustomSteps scenario,
                         JBddBusinessSteps jBddBusinessSteps,
                         JBddStandardContextFactory standardContextFactory) {
        this.scenario = scenario;
        this.jBddStandardContext = standardContextFactory.create();
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
}
