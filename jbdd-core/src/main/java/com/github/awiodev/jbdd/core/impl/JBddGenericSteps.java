package com.github.awiodev.jbdd.core.impl;

import com.github.awiodev.jbdd.core.definition.JBddCallable;
import com.github.awiodev.jbdd.core.definition.JBddRunnable;
import com.github.awiodev.jbdd.core.definition.JBddSteps;

public final class JBddGenericSteps<TSteps extends JBddSteps<?>>
    implements JBddSteps<TSteps> {

    private final TSteps chainParent;

    public JBddGenericSteps(TSteps chainParent) {
        this.chainParent = chainParent;
    }

    public TSteps given(String given, JBddRunnable runnable) {
        return runnable(runnable);
    }

    public TSteps when(String when, JBddRunnable runnable) {
        return runnable(runnable);
    }

    public TSteps then(String then, JBddRunnable runnable) {
        return runnable(runnable);
    }

    public TSteps and(String and, JBddRunnable runnable) {
        return runnable(runnable);
    }

    private TSteps runnable(JBddRunnable runnable) {
        runnable.perform();
        return chainParent;
    }

    public <T> T given(String given, JBddCallable<T> callable) {
        return callable(callable);
    }

    public <T> T when(String when, JBddCallable<T> callable) {
        return callable(callable);
    }

    public <T> T then(String then, JBddCallable<T> callable) {
        return callable(callable);
    }

    public <T> T and(String and, JBddCallable<T> callable) {
        return callable(callable);
    }

    private <T> T callable(JBddCallable<T> callable) {
        return callable.perform();
    }
}
