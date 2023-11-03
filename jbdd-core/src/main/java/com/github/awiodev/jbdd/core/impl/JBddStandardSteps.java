package com.github.awiodev.jbdd.core.impl;

import com.github.awiodev.jbdd.core.definition.JBddCallable;
import com.github.awiodev.jbdd.core.definition.JBddRunnable;
import com.github.awiodev.jbdd.core.definition.JBddSteps;

public class JBddStandardSteps implements JBddSteps<JBddStandardSteps> {

    private final JBddSteps<JBddStandardSteps> parent;

    private JBddStandardSteps() {
        parent = new JBddGenericSteps<>(this);
    }

    private JBddStandardSteps(JBddSteps<JBddStandardSteps> parent) {
        this.parent = parent;
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
        private JBddSteps<JBddStandardSteps> parent;

        private JBddStepsBuilder() {
        }

        public JBddStepsBuilder withParent(JBddSteps<JBddStandardSteps> parent) {
            this.parent = parent;
            return this;
        }

        public JBddStandardSteps build() {
            if (parent == null) {
                return new JBddStandardSteps();
            }
            return new JBddStandardSteps(parent);
        }
    }
}
