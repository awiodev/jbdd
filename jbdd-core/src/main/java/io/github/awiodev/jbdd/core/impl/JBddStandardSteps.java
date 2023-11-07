package io.github.awiodev.jbdd.core.impl;

import io.github.awiodev.jbdd.core.definition.JBddCallable;
import io.github.awiodev.jbdd.core.definition.JBddRunnable;
import io.github.awiodev.jbdd.core.definition.JBddSteps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JBddStandardSteps implements JBddSteps<JBddStandardSteps> {

    private static final Logger LOGGER = LoggerFactory.getLogger(JBddStandardSteps.class);

    private final JBddSteps<JBddStandardSteps> parent;

    private JBddStandardSteps() {
        parent = new JBddGenericSteps<>(this);
    }

    @Override
    public JBddStandardSteps given(String given, JBddRunnable runnable) {
        return parent.given(given, runnable);
    }

    @Override
    public JBddStandardSteps when(String when, JBddRunnable runnable) {
        return parent.when(when, runnable);
    }

    @Override
    public JBddStandardSteps then(String then, JBddRunnable runnable) {
        return parent.then(then, runnable);
    }

    @Override
    public JBddStandardSteps and(String and, JBddRunnable runnable) {
        return parent.and(and, runnable);
    }

    @Override
    public <T> T given(String given, JBddCallable<T> callable) {
        return parent.given(given, callable);
    }

    @Override
    public <T> T when(String when, JBddCallable<T> callable) {
        return parent.when(when, callable);
    }

    @Override
    public <T> T then(String then, JBddCallable<T> callable) {
        return parent.then(then, callable);
    }

    @Override
    public <T> T and(String and, JBddCallable<T> callable) {
        return parent.and(and, callable);
    }

    public static JBddStepsBuilder builder() {
        return new JBddStepsBuilder();
    }

    public static final class JBddStepsBuilder {

        private JBddStepsBuilder() {
        }

        public JBddStandardSteps build() {
            return new JBddStandardSteps();
        }
    }
}
