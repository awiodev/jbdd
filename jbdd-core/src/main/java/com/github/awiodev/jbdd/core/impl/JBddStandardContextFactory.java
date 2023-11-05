package com.github.awiodev.jbdd.core.impl;

import com.github.awiodev.jbdd.core.definition.JBddContextFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JBddStandardContextFactory implements JBddContextFactory<JBddStandardContext> {

    private static final Logger LOGGER = LoggerFactory.getLogger(JBddStandardContextFactory.class);

    private JBddStandardContextFactory() {}

    @Override
    public JBddStandardContext create() {
        JBddStandardContext instance = JBddStandardContext.builder().build();
        LOGGER.debug("Providing context instance: {}", instance);
        return instance;
    }

    public static JBddStandardContextFactoryBuilder builder() {
        return new JBddStandardContextFactoryBuilder();
    }


    public static final class JBddStandardContextFactoryBuilder {

        private JBddStandardContextFactoryBuilder() {
        }

        public JBddStandardContextFactory build() {
            return new JBddStandardContextFactory();
        }
    }
}
