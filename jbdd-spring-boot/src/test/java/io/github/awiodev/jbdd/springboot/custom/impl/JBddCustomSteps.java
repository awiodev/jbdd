package io.github.awiodev.jbdd.springboot.custom.impl;

import io.github.awiodev.jbdd.core.definition.JBddCallable;
import io.github.awiodev.jbdd.core.definition.JBddRunnable;
import io.github.awiodev.jbdd.core.definition.JBddSteps;

public class JBddCustomSteps implements JBddSteps<JBddCustomSteps> {

    @Override
    public JBddCustomSteps given(String given, JBddRunnable runnable) {
        return this;
    }

    @Override
    public JBddCustomSteps when(String when, JBddRunnable runnable) {
        return this;
    }

    @Override
    public JBddCustomSteps then(String then, JBddRunnable runnable) {
        return this;
    }

    @Override
    public JBddCustomSteps and(String and, JBddRunnable runnable) {
        return this;
    }

    @Override
    public <T> T given(String given, JBddCallable<T> callable) {
        return null;
    }

    @Override
    public <T> T when(String when, JBddCallable<T> callable) {
        return null;
    }

    @Override
    public <T> T then(String then, JBddCallable<T> callable) {
        return null;
    }

    @Override
    public <T> T and(String and, JBddCallable<T> callable) {
        return null;
    }
}
