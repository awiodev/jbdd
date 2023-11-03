package com.github.awiodev.jbdd.core.definition;

/**
 * Provider of unique test context.
 * @param <TContext> for context type
 */
@FunctionalInterface
public interface JBddContextProvider<TContext extends JBddContext> {

    /**
     * Provides unique context instance.
     * @return given context type
     */
    TContext provide();
}
