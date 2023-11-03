package com.github.awiodev.jbdd.core.impl;

import com.github.awiodev.jbdd.core.definition.JBddContext;
import com.github.awiodev.jbdd.core.definition.JBddRun;
import com.github.awiodev.jbdd.core.definition.JBddSteps;

public class JBdd implements JBddRun<JBddSteps<?>, JBddContext> {

    private final JBddSteps<?> steps;

    private final JBddContext context;

    private JBdd(JBddSteps<?> steps, JBddContext context) {
        this.steps = steps;
        this.context = context;
    }

    @Override
    public JBddSteps<?> scenario() {
        return steps;
    }

    @Override
    public JBddContext context() {
        return context;
    }

    @Override
    public void clean() {
    }

    public static JBddRunBuilder builder() {
        return new JBddRunBuilder();
    }

    public static final class JBddRunBuilder {
        private JBddSteps<?> steps;
        private JBddContext context;

        private JBddRunBuilder() {
        }

        public JBddRunBuilder withSteps(JBddSteps<?> steps) {
            this.steps = steps;
            return this;
        }

        public JBddRunBuilder withContext(JBddContext context) {
            this.context = context;
            return this;
        }

        public JBdd build() {

            if (steps == null) {
                steps = JBddStandardSteps.builder().build();
            }

            if (context == null) {
                context = JBddStandardContext.builder().build();
            }

            return new JBdd(steps, context);
        }
    }
}
