package io.github.awiodev.jbdd.core.definition;

/**
 * Functional interface for a function that returns given value executed inside step.
 * @param <T> represents type of returned value
 */
@FunctionalInterface
public interface JBddCallable<T> {

    /**
     * Executes a function that returns given value.
     * @return value
     */
    T perform();
}
