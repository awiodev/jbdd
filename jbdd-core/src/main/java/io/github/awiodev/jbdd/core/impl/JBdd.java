package io.github.awiodev.jbdd.core.impl;

import io.github.awiodev.jbdd.core.definition.JBddContext;
import io.github.awiodev.jbdd.core.definition.JBddContextFactory;
import io.github.awiodev.jbdd.core.definition.JBddRun;
import io.github.awiodev.jbdd.core.definition.JBddSteps;
import io.github.awiodev.jbdd.core.definition.Steps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JBdd implements JBddRun<JBddSteps<? extends Steps>, JBddContext> {

    private static final Logger LOGGER = LoggerFactory.getLogger(JBdd.class);

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

    public static JBddRunBuilder builder() {
        return new JBddRunBuilder();
    }

    public static final class JBddRunBuilder {
        private JBddSteps<?> steps;
        private JBddContextFactory<?> contextFactory;

        private JBddRunBuilder() {
        }

        public JBddRunBuilder withSteps(JBddSteps<?> steps) {
            this.steps = steps;
            LOGGER.debug("Provided step instance: {}", steps);
            return this;
        }

        public JBddRunBuilder withContextFactory(JBddContextFactory<?> contextFactory) {
            this.contextFactory = contextFactory;
            LOGGER.debug("Provided context provider instance: {}", contextFactory);
            return this;
        }

        public JBdd build() {

            if (steps == null) {
                LOGGER.info("Steps not provided. JBddStandardSteps will be used as default");
                steps = JBddStandardSteps.builder().build();
            }

            JBddContext context;

            if (contextFactory == null) {
                LOGGER.info(
                    "Context factory not provided. JBddStandardContext will be used as default");
                context = JBddStandardContext.builder().build();
            } else {
                LOGGER.info("Creating new context using context factory");
                context = contextFactory.create();
            }

            return new JBdd(steps, context);
        }
    }
}
